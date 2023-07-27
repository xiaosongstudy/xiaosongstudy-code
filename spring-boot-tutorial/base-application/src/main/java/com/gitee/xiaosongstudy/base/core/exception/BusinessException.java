package com.gitee.xiaosongstudy.base.core.exception;

import java.util.Map;

/**
 * 业务异常类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 10:49
 */
public class BusinessException extends GlobalException {

    private static final long serialVersionUID = 5337782198493125133L;

    public BusinessException(String message, Throwable cause, Map<String, String> model) {
        super(message, cause, model);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Map<String, String> model) {
        super(message, model);
    }
}
