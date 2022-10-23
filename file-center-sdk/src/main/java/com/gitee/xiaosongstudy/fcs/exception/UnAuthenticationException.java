package com.gitee.xiaosongstudy.fcs.exception;

import com.gitee.xiaosongstudy.fcs.common.ResponseStatusEnum;

/**
 * 未认证异常 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 14:38
 */
public class UnAuthenticationException extends RuntimeException{
    private static final long serialVersionUID = -7674165291073195079L;

    public UnAuthenticationException() {
        super(ResponseStatusEnum.UN_AUTHENTICATION.getDescription());
    }
}
