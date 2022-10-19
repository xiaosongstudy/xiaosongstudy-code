package com.gitee.xiaosongstudy.hopeurlfilecenter.exception;

/**
 * 业务参数异常 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 10:49
 */
public class BusinessParamException extends RuntimeException{

    private static final long serialVersionUID = 5337782198493125133L;

    public BusinessParamException(String message) {
        super(message);
    }
}
