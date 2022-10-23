package com.gitee.xiaosongstudy.fcs.exception;

/**
 * 业务异常类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 10:49
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 5337782198493125133L;

    public BusinessException(String message) {
        super(message);
    }
}
