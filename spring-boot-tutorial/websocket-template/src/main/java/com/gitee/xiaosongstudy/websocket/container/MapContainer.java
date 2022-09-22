package com.gitee.xiaosongstudy.websocket.container;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
     * 通过分类和hash key获取对应的缓存值
     * @param tags 当前缓存所属归档
     * @param key 键
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
