package com.gitee.xiaosongstudy.fcs.common;

import lombok.Getter;

/**
 * 响应状态枚举 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 13:17
 */
@Getter
public enum ResponseStatusEnum {
    /**
     * 全局响应状态码
     */
    UN_AUTHENTICATION(401, "认证失败！"),
    FORBIDDEN(403, "无权限！"),
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
