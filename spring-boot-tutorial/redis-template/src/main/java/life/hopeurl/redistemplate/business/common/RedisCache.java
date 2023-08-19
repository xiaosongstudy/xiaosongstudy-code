package life.hopeurl.redistemplate.business.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存
 *
 * @author shiping.song
 * @date 2023/8/19 13:54
 * @email 2453332538@qq.com
 */
@Data
@Builder
public class RedisCache implements Serializable {
    private static final long serialVersionUID = 1850125097494114347L;

    /**
     * 缓存前缀
     */
    private String businessPrefix;

    /**
     * 业务键
     */
    private String businessKey;

    /**
     * 过期时间
     */
    private Long ttl;

    /**
     * 过期时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 逻辑过期时间
     */
    private long expireTime;

    /**
     * 负载数据
     */
    private Object value;

    /**
     * hash key
     */
    private String hashKey;

    /**
     * hash value
     */
    private Object hashValue;
}
