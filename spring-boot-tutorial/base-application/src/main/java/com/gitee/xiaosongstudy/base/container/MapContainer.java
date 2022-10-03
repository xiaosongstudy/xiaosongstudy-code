package com.gitee.xiaosongstudy.base.container;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * map容器 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 22:03
 */
@Component
public class MapContainer {

    @Resource(type = RedisTemplate.class)
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置值
     *
     * @param tags  当前key应该归属哪一档
     * @param key   键
     * @param value 值
     */
    public void setValue(String tags, String key, Object value) {
        redisTemplate.opsForHash().put(getPrefix() + tags, key, value);
    }

    /**
     * 批量设置值
     *
     * @param tags 前key应该归属哪一档
     * @param data 数据
     */
    public void setValue(String tags, Map<String, Object> data) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            Map<byte[], byte[]> insertData = new HashMap<>();
            data.forEach((k, v) -> {
                insertData.put(k.getBytes(StandardCharsets.UTF_8), v.toString().getBytes(StandardCharsets.UTF_8));
            });
            connection.hMSet((getPrefix() + tags).getBytes(StandardCharsets.UTF_8), insertData);
            return null;
        });
    }

    /**
     * 通过分类和hash key获取对应的缓存值
     *
     * @param tags 当前缓存所属归档
     * @param key  键
     * @return 获取目标值
     */
    public Object getValue(String tags, String key) {
        return redisTemplate.opsForHash().get(tags, key);
    }

    /**
     * 获取map key前缀
     *
     * @return
     */
    private String getPrefix() {
        return MapContainer.class.getSimpleName() + ":";
    }
}
