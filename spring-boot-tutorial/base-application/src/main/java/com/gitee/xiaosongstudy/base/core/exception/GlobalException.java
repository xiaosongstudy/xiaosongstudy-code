package com.gitee.xiaosongstudy.base.core.exception;

import java.util.Map;

/**
 * 全局异常基类
 *
 * @author shiping.song
 * @date 2023/7/27 21:03
 * @email 2453332538@qq.com
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -7720932329744553277L;


    private final Map<String, String> model;

    public GlobalException(String message, Throwable cause, Map<String, String> model) {
        super(message, cause);
        this.model = model;
    }

    public GlobalException(String message, Throwable cause) {
        this(message, cause, null);
    }

    public GlobalException(String message, Map<String, String> model) {
        super(message);
        this.model = model;
    }

    public GlobalException(String message) {
        super(message);
        this.model = null;
    }
}
