package com.gitee.xiaosongstudy.base.core.exception;

/**
 * 回调失败异常.<br>
 *
 * @author shiping.song
 * @date 2023/4/2 10:43
 */
public class CallBackFailException extends Exception{
    private static final long serialVersionUID = -2033714175540140147L;

    public CallBackFailException(String message) {
        super(message);
    }

    public CallBackFailException() {
    }
}
