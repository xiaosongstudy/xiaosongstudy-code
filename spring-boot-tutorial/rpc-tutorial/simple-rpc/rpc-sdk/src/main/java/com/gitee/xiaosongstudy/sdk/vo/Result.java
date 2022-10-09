package com.gitee.xiaosongstudy.sdk.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hopeurl
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = -4830756192458102136L;

    /**
     * 响应标识
     */
    private Boolean flag;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 响应参数类型
     */
    private Class<?>[] resultParamTypes;

    /**
     * 响应参数值
     */
    private Object[] resultArgs;
}
