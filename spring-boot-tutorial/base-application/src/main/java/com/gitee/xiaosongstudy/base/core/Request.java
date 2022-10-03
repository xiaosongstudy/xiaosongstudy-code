package com.gitee.xiaosongstudy.base.core;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求对象 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 11:16
 */
@Data
public class Request implements Serializable {
    private static final long serialVersionUID = 4211818795163380834L;

    /**
     * 请求数据
     */
    private String data;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页多少条
     */
    private Integer limit;
    /**
     * 排序条件
     */
    private String sort;
}
