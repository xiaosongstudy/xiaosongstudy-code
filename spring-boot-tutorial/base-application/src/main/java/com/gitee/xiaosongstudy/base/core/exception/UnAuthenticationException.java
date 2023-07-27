package com.gitee.xiaosongstudy.base.core.exception;

import java.util.Map;

/**
 * 未认证异常 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 14:38
 */
public class UnAuthenticationException extends GlobalException {
    private static final long serialVersionUID = -7674165291073195079L;

    public UnAuthenticationException(String message, Throwable cause, Map<String, String> model) {
        super(message, cause, model);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(String message, Map<String, String> model) {
        super(message, model);
    }

    public UnAuthenticationException(String message) {
        super(message);
    }
}
