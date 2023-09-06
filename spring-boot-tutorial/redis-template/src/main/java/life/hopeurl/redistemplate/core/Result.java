package life.hopeurl.redistemplate.core;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局统一响应类
 *
 * @author shiping.song
 * @date 2023/9/6 23:59
 * @email 2453332538@qq.com
 */
@Data
@Builder
public class Result<T> implements Serializable {


    private static final long serialVersionUID = -200103498788767279L;

    /**
     * 处理标识
     */
    private boolean flag;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;
}
