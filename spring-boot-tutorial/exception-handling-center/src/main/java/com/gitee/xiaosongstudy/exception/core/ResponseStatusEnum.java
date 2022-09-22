package com.gitee.xiaosongstudy.exception.core;

import lombok.Getter;

/**
 * 响应状态枚举 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 13:17
 */
@Getter
public enum ResponseStatusEnum{
    // 其余常用状态可参考 org.springframework.http.HttpStatus
    SUCCESS(10000, "业务处理成功！"),
    FAILURE(10001, "业务处理失败！"),
    PARAM_ERROR(10002, "业务参数错误！");

    /**
     * 状态码
     */
    private int code;

    /**
     * 描述
     */
    private String description;

    ResponseStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
