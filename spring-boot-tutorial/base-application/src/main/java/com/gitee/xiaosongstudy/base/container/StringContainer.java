package com.gitee.xiaosongstudy.base.container;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * string容器 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 22:03
 */
@Component
public class StringContainer {

    @Resource(type = StringRedisTemplate.class)
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置值
     *
     * @param key   缓存键
     * @param value 缓存值
     */
    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(getPrefix() + key, value);
    }

    /**
     * 设置 key的值同时设置过期时间
     *
     * @param key        缓存键
     * @param value      缓存值
     * @param expireTime 过期时间
     * @param timeUnit   时间单位
     */
    public void setValue(String key, String value, long expireTime, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(getPrefix() + key, value, expireTime, timeUnit);
    }

    /**
     * 通过键获取值
     *
     * @param key 键
     * @return 值
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(getPrefix() + key);
    }

    /**
     * 获取容器前缀
     *
     * @return 获取前缀名称
     */
    private String getPrefix() {
        return StringContainer.class.getSimpleName() + ":";
    }
}
