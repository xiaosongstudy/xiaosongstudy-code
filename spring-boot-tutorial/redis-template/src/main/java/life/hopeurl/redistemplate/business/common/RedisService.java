package life.hopeurl.redistemplate.business.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import life.hopeurl.redistemplate.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>redis服务类</p>
 * <p>在如下系列方法中，如果设置了过期时间但是没有指定单位则默认为分钟</p>
 *
 * @author shiping.song
 * @date 2023/8/19 13:50
 * @email 2453332538@qq.com
 */
@Service
@Slf4j
public class RedisService {

    /**
     * 主键开始时间
     */
    private static final long NEXT_LONG_ID_START = 1692919237;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置缓存
     *
     * @param redisCache 缓存辅助变量
     * @date 2023/8/20 00:07
     */
    public void set(RedisCache redisCache) {
        String realKey = this.getRealKey(redisCache);
        Long ttl = redisCache.getTtl();
        if (Objects.nonNull(ttl)) {
            TimeUnit timeUnit = this.mergeTimeUnit(redisCache);
            stringRedisTemplate.opsForValue().set(realKey, this.mergeStringValue(redisCache.getValue()), ttl, timeUnit);
        } else {
            stringRedisTemplate.opsForValue().set(realKey, this.mergeStringValue(redisCache.getValue()));
        }
    }

    /**
     * 获取缓存
     *
     * @param redisCache 缓存辅助变量
     * @return 缓存值
     * @date 2023/8/21 23:10
     */
    public String get(RedisCache redisCache) {
        String realKey = this.getRealKey(redisCache);
        return stringRedisTemplate.opsForValue().get(realKey);
    }

    /**
     * 设置缓存，同时设置逻辑过期时间
     *
     * @param redisCache 缓存辅助变量
     * @date 2023/8/21 01:42
     */
    public void setWithLogicTime(RedisCache redisCache) {
        String realKey = this.getRealKey(redisCache);
        assert Objects.nonNull(redisCache.getExpireTime()) && Objects.nonNull(redisCache.getValue());
        RedisCache data = RedisCache.builder().expireTime(redisCache.getExpireTime()).value(redisCache.getValue()).build();
        stringRedisTemplate.opsForValue().set(realKey, this.mergeStringValue(data));
    }

    /**
     * 设置缓存，同时加上互斥锁，注意：这个方法会阻塞线程，如果无法从缓存中获取到数据将会一直等待缓存被某一个线程设置完才会释放
     *
     * @param redisCache  缓存辅助变量
     * @param expireRule  redis中的缓存是否过期
     * @param valueMapper 根据缓存是否过期来动态处理返回值
     * @date 2023/8/20 17:33
     */
    public <R> R setWithLock(RedisCache redisCache, Supplier<Boolean> expireRule, Function<Boolean, R> valueMapper) {
        assert Objects.nonNull(expireRule) && Objects.nonNull(valueMapper);
        boolean expired = expireRule.get();
        if (expired) {
            // 如果没有结束则需要获取锁并生成数据，同时没有获取到的线程需要循环等待
            RedisCache lockCache = this.getLockCache(redisCache.getBusinessPrefix(), redisCache.getBusinessPrefix());
            boolean locked = this.setNx(lockCache);
            if (locked) {
                expired = expireRule.get();
                // 这里使用经典的双空校验
                if (expired) {
                    try {
                        return valueMapper.apply(true);
                    } finally {
                        // 释放锁
                        stringRedisTemplate.delete(this.getRealKey(lockCache));
                    }
                }
            } else {
                // 睡眠300ms
                try {
                    TimeUnit.MILLISECONDS.sleep(300L);
                    this.setWithLock(redisCache, expireRule, valueMapper);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return valueMapper.apply(false);
    }


    /**
     * <p>加锁，然后更新缓存，注意：使用本方法要确保缓存中已经提前初始化好了相对应的热点key</p>
     * <p>本方法使用的是逻辑过期的思想来解决缓存穿透问题，因而数据具有一定的延迟性，使用本方法时要综合考虑这块对于应用的负面影响</p>
     *
     * @param redisCache
     * @param valueMapper
     * @param <R>
     * @return
     * @date 2023/8/21 22:21
     */
    public <R> R strLockThenUpdateCache(RedisCache redisCache, Function<Object, R> valueMapper, Consumer<RedisCache> cacheUpdate) {
        RedisCache logicCacheData = this.getLogicCacheData(redisCache);
        LocalDateTime expireTime = logicCacheData.getExpireTime();
        if (Objects.isNull(expireTime)) {
            this.illegalArgumentException("逻辑过期字段【expireTime】没有值，请检查");
        } else {
            // 如果已经过期了则需要更新
            LocalDateTime now = LocalDateTime.now();
            if (expireTime.isAfter(now)) {
                // 尝试获取锁
                RedisCache lockCache = this.getLockCache(redisCache.getBusinessPrefix(), redisCache.getBusinessPrefix());
                boolean locked = this.setNx(lockCache);
                if (locked) {
                    try {
                        // 只尝试获取一次锁，如果没获取上就不管了，直接返回旧值
                        logicCacheData = this.getLogicCacheData(redisCache);
                        if (redisCache.getExpireTime().isAfter(now)) {
                            // 开启线程进行处理
                            new Thread(() -> {
                                // 正常执行完毕，必须释放锁
                                try {
                                    cacheUpdate.accept(redisCache);
                                } finally {
                                    stringRedisTemplate.delete(this.getRealKey(lockCache));
                                }
                            }).start();
                        }
                    } catch (Exception e) {
                        // 如果抛出异常则先释放锁，然后再将异常重新跑出去，正常执行不释放锁
                        stringRedisTemplate.delete(this.getRealKey(lockCache));
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return valueMapper.apply(logicCacheData.getValue());
    }

    /**
     * 获取逻辑缓存数据
     *
     * @param redisCache redis缓存数据
     * @return 缓存数据
     * @date 2023/8/21 23:38
     */
    private RedisCache getLogicCacheData(RedisCache redisCache) {
        String cache = this.get(redisCache);
        assert StrUtil.isNotBlank(cache);
        // 校验是否有过期时间字段
        RedisCache cacheData = JSONUtil.toBean(cache, RedisCache.class);
        this.illegalArgumentException(Objects.isNull(cacheData), "缓存数据不存在");
        this.illegalArgumentException(Objects.isNull(cacheData.getExpireTime()), "逻辑过期字段【expireTime】没有值，请检查");
        return cacheData;
    }


    /**
     * 设置缓存，并返回设置结果，返回false时则设置失败
     *
     * @param redisCache 缓存辅助变量
     * @return 设置结果
     * @date 2023/8/20 00:30
     */
    public boolean setNx(RedisCache redisCache) {
        String realKey = this.getRealKey(redisCache);
        Long ttl = redisCache.getTtl();
        Boolean result;
        if (Objects.nonNull(ttl)) {
            TimeUnit timeUnit = this.mergeTimeUnit(redisCache);
            result = stringRedisTemplate.opsForValue().setIfAbsent(realKey, this.mergeStringValue(redisCache.getValue()), ttl, timeUnit);
        } else {
            result = stringRedisTemplate.opsForValue().setIfAbsent(realKey, this.mergeStringValue(redisCache.getValue()));
        }
        return BooleanUtil.isTrue(result);
    }

    /**
     * 设置缓存，数据结构使用的是hash，单个设置使用hashKey以及hashValue
     *
     * @param redisCache 缓存辅助变量
     * @date 2023/8/20 00:53
     */
    public void put(RedisCache redisCache) {
        String realKey = this.getRealKey(redisCache);
        assert null != redisCache.getHashValue() && StrUtil.isNotBlank(redisCache.getHashKey());
        stringRedisTemplate.opsForHash().put(realKey, redisCache.getHashKey(), this.mergeStringValue(redisCache.getHashValue()));
        Long ttl = redisCache.getTtl();
        if (Objects.nonNull(ttl)) {
            stringRedisTemplate.expire(realKey, ttl, this.mergeTimeUnit(redisCache));
        }
    }

    /**
     * 设置缓存，数据结构使用的是hash，批量设置依托于value参数
     *
     * @param redisCache 缓存辅助变量
     * @date 2023/8/20 01:01
     */
    public void putAll(RedisCache redisCache) {
        String realKey = this.getRealKey(redisCache);
        assert Objects.nonNull(redisCache.getValue());
        Map<String, Object> dataMap = BeanUtil.beanToMap(redisCache.getValue(),
                new HashMap<>(), CopyOptions.create().ignoreNullValue().setFieldValueEditor((fieldName, fieldValue) -> {
                    // ignoreNullValue优先级低于setFieldValueEditor，因此这里需要再进行一次判断
                    if (Objects.nonNull(fieldValue)) {
                        return this.mergeStringValue(fieldValue);
                    }
                    return null;
                }));
        stringRedisTemplate.opsForHash().putAll(realKey, dataMap);
        Long ttl = redisCache.getTtl();
        if (Objects.nonNull(ttl)) {
            stringRedisTemplate.expire(realKey, ttl, this.mergeTimeUnit(redisCache));
        }
    }

    /**
     * 锁定数据，如果为true则锁定成功
     *
     * @param businessPrefix 业务前缀
     * @param businessKey    业务键
     * @return 锁定结果
     * @date 2023/8/20 16:43
     */
    public boolean lock(String businessPrefix, String businessKey) {
        return this.setNx(this.getLockCache(businessPrefix, businessKey));
    }

    /**
     * 释放key
     *
     * @param businessPrefix 业务前缀
     * @param businessKey    业务键
     * @date 2023/8/20 16:54
     */
    public void unLock(String businessPrefix, String businessKey) {
        assert StrUtil.isAllNotBlank(businessPrefix, businessKey);
        stringRedisTemplate.delete(businessPrefix + businessKey);
    }


    /**
     * redis全局自增主键
     *
     * @param businessKey 业务key
     * @return 当前全局主键
     * @date 2023/8/24 23:23
     */
    public long nextLongId(String businessKey) {
        LocalDateTime now = LocalDateTime.now();
        String realKey = RedisConstant.NEXT_LONG_KEY + now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd")) + ":" + businessKey;
        long timestamp = now.toEpochSecond(ZoneOffset.UTC) - NEXT_LONG_ID_START;
        long increment = stringRedisTemplate.opsForValue().increment(realKey);
        return timestamp << 32 | increment;
    }

    /**
     * 获取锁并进行缓存操作
     *
     * @param businessPrefix 业务前缀
     * @param businessKey    业务键
     * @param callBack       缓存处理回调
     * @date 2023/8/20 17:08
     */
    public void operationWithLock(String businessPrefix, String businessKey, Consumer<Boolean> callBack) {
        assert StrUtil.isAllNotBlank(businessPrefix, businessKey);
        try {
            callBack.accept(this.lock(businessPrefix, businessKey));
        } finally {
            this.unLock(businessPrefix, businessKey);
        }
    }

    /**
     * 获取加锁缓存对象
     *
     * @param businessPrefix 业务前缀
     * @param businessKey    业务键
     * @return 缓存对象
     * @date 2023/8/20 17:17
     */
    private RedisCache getLockCache(String businessPrefix, String businessKey) {
        return RedisCache.builder()
                .businessPrefix(businessPrefix)
                .businessKey(businessKey).value("locked:" + DateUtil.formatLocalDateTime(LocalDateTime.now())).build();
    }


    /**
     * 获取真实key
     *
     * @param redisCache 缓存辅助变量
     * @return 真实key
     * @date 2023/8/20 00:22
     */
    private String getRealKey(RedisCache redisCache) {
        assert null != redisCache &&
                null != redisCache.getValue() &&
                StrUtil.isAllNotBlank(redisCache.getBusinessPrefix(), redisCache.getBusinessKey());
        return redisCache.getBusinessPrefix() + redisCache.getBusinessKey();
    }

    /**
     * 合并时间单位
     *
     * @param redisCache 缓存辅助变量
     * @return 合并后的时间单位
     * @date 2023/8/20 00:33
     */
    private TimeUnit mergeTimeUnit(RedisCache redisCache) {
        return Optional.ofNullable(redisCache.getTimeUnit()).orElse(TimeUnit.MINUTES);
    }

    /**
     * 将data的值合并为字符串类型，合并规则为：如果data为字符串则直接存储，如果data为字符串则直接存储，如果是包装类型则调用toString方法，其它则转化为json格式
     *
     * @param data 数据源
     * @return 合并结果
     * @date 2023/8/20 00:40
     */
    private String mergeStringValue(Object data) {
        assert null != data;
        String value;
        if (data instanceof String || data instanceof Number) {
            value = data.toString();
        } else {
            value = JSONUtil.toJsonStr(data);
        }
        return value;
    }

    /**
     * 非法参数异常
     *
     * @param isThrow 是否抛出异常
     * @param message 错误信息
     * @date 2023/8/21 23:31
     */
    private void illegalArgumentException(boolean isThrow, String message) {
        if (isThrow) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 非法参数异常
     *
     * @param message 异常信息
     * @date 2023/8/21 23:21
     */
    private void illegalArgumentException(String message) {
        this.illegalArgumentException(true, message);
    }
}
