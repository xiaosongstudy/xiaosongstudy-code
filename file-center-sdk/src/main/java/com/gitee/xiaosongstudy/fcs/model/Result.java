package com.gitee.xiaosongstudy.fcs.model;

import com.gitee.xiaosongstudy.fcs.common.ResponseStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局请求响应.<br>
 *
 * @author shiping.song
 * @date 2022/10/15 16:22
 */
@Data
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = 8735359107876256340L;

    /**
     * 响应结果(逻辑成功或者失败)
     */
    private Boolean flag;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private String data;
    /**
     * 总数据量
     */
    private Long totalCount;

    /**
     * 操作成功
     *
     * @param code       状态码
     * @param data       响应数据
     * @param totalCount 数据总量
     * @return 响应结果
     */
    private static Result success(Integer code, String data, Long totalCount) {
        return Result.builder().code(code).data(data).totalCount(totalCount).flag(true).build();
    }

    /**
     * 操作失败
     *
     * @param code    状态码
     * @param message 错误信息
     * @return 响应结果
     */
    public static Result failure(Integer code, String message) {
        return Result.builder().code(code).msg(message).flag(false).build();
    }

    /**
     * 操作失败-有错误信息
     * @param message 错误信息
     * @return 响应结果
     */
    public static Result failure(String message) {
        return failure(ResponseStatusEnum.FAILURE.getCode(), message);
    }

    /**
     * 操作失败-无错误信息
     * @return 响应结果
     */
    public static Result failure() {
        return failure(ResponseStatusEnum.FAILURE.getDescription());
    }

    /**
     * 操作成功-有响应数据
     *
     * @param data 响应数据
     * @return 响应结果
     */
    public static Result success(String data) {
        return success(ResponseStatusEnum.SUCCESS.getCode(), data, null);
    }

    /**
     * 操作成功-无响应数据
     *
     * @return 响应结果
     */
    public static Result success() {
        return success(null);
    }
}
