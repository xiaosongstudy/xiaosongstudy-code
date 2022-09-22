package com.gitee.xiaosongstudy.exception.core;

/**
 * 未认证异常 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 14:38
 */
public class UnAuthenticationException extends RuntimeException{
    private static final long serialVersionUID = -7674165291073195079L;

    public UnAuthenticationException(String message) {
        super(message);
    }
}
