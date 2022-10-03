package com.gitee.xiaosongstudy.security.constant;

import org.springframework.http.HttpHeaders;

/**
 * 全局常量类 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/2 19:16
 */
public class Globals {

    private Globals() {

    }

    /**
     * 用户携带的access_token标识
     */
    public static final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;

    /**
     * bearer前缀
     */
    public static final String BEARER = "Bearer ";

    /**
     * 权限信息
     */
    public static final String AUTHORITIES = "authorities";

    /**
     * name值
     */
    public static final String NAME = "name";

    /**
     * 分隔符-逗号
     */
    public static final String COMMA = ",";
}
