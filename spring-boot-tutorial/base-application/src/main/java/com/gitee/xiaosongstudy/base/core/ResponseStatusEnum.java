package com.gitee.xiaosongstudy.base.core;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 响应状态枚举 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 13:17
 */
@Getter
public enum ResponseStatusEnum{
    // 其余常用状态可参考 org.springframework.http.HttpStatus
    UN_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), "认证失败！"),
    SUCCESS(10000, "业务处理成功！"),
    FAILURE(10001, "业务处理失败！"),
    PARAM_ERROR(10002, "业务参数错误！");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 描述
     */
    private final String description;

    ResponseStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
