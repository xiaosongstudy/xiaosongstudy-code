package com.gitee.xiaosongstudy.base.container;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    public void setValue(String tags, Map<String, String> data) {
        String prefix = this.getPrefix();
        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForHash().putAll(prefix + tags, data);
                return null;
            }
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
        return redisTemplate.opsForHash().get(this.getPrefix() + tags, key);
    }

    /**
     * 获取指定分类下的所有缓存数据
     *
     * @param tags 指定分类
     * @return 所有符合条件的数据
     */
    public Object getValues(String tags) {
        return redisTemplate.opsForHash().values(this.getPrefix() + tags);
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
