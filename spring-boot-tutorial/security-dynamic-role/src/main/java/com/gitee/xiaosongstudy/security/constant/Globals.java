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

    /**
     * redis key 接口权限信息
     */
    public static final String INTERFACE_PERMS = "interface_perms";

    /**
     * access_token
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * refresh_token
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * 当前接口权限标识
     */
    public static final String PERMS_TYPE = "perms_type";

    /**
     * 标识类
     */
    public static class Flag {
        /**
         * 断言为真
         */
        public static final String TRUE = "1";
        /**
         * 断言为假
         */
        public static final String FALSE = "0";
    }
}
