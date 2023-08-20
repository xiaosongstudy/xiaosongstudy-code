package life.hopeurl.redistemplate.business.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
     * 设置缓存，同时加上互斥锁，注意：这个方法会阻塞线程，如果无法从缓存中获取到数据将会一直等待缓存被某一个线程设置完才会释放
     *
     * @param redisCache 缓存辅助变量
     * @date 2023/8/20 17:33
     */
    public <R, DB> R setWithLock(RedisCache redisCache, Supplier<R> cacheData, Supplier<DB> dataBase, Function<DB, R> setValueHandler) {
        assert Objects.nonNull(cacheData) && Objects.nonNull(dataBase) && Objects.nonNull(setValueHandler);
        R cache = cacheData.get();
        if (Objects.isNull(cache)) {
            // 如果没有结束则需要获取锁并生成数据，同时没有获取到的线程需要循环等待
            RedisCache lockCache = this.getLockCache(redisCache.getBusinessPrefix(), redisCache.getBusinessPrefix());
            boolean locked = this.setNx(lockCache);
            if (locked) {
                cache = cacheData.get();
                // 这里使用经典的双空校验
                if (Objects.isNull(cache)) {
                    return setValueHandler.apply(dataBase.get());
                }
            } else {
                // 睡眠300ms
                try {
                    TimeUnit.MILLISECONDS.sleep(300L);
                    this.setWithLock(redisCache, cacheData, dataBase, setValueHandler);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return cache;
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
}
