package com.gitee.xiaosongstudy.hopeurlfilecenter.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 结果响应视图
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 13:33
 */
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 791651977696584448L;
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }
}