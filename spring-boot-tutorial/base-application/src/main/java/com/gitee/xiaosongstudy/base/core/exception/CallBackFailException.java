package com.gitee.xiaosongstudy.base.core.exception;

import java.util.Map;

/**
 * 回调失败异常.<br>
 *
 * @author shiping.song
 * @date 2023/4/2 10:43
 */
public class CallBackFailException extends GlobalException {
    private static final long serialVersionUID = -2033714175540140147L;

    public CallBackFailException(String message, Throwable cause, Map<String, String> model) {
        super(message, cause, model);
    }

    public CallBackFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallBackFailException(String message, Map<String, String> model) {
        super(message, model);
    }

    public CallBackFailException(String message) {
        super(message);
    }
}
