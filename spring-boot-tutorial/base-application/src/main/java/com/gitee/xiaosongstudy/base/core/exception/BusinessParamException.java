package com.gitee.xiaosongstudy.base.core.exception;

import java.util.Map;

/**
 * 业务参数异常 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 10:49
 */
public class BusinessParamException extends GlobalException{

    private static final long serialVersionUID = 5337782198493125133L;

    public BusinessParamException(String message, Throwable cause, Map<String, String> model) {
        super(message, cause, model);
    }

    public BusinessParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessParamException(String message, Map<String, String> model) {
        super(message, model);
    }

    public BusinessParamException(String message) {
        super(message);
    }
}
