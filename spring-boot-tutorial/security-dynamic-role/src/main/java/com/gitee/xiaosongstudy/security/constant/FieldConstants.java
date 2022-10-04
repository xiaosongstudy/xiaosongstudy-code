package com.gitee.xiaosongstudy.security.constant;

/**
 * 属性常量类 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/4 1:31
 */
public class FieldConstants {
    private FieldConstants() {

    }

    /**
     * 接口类相关常量
     */
    public static final class SecInterface {
        /**
         * 同时满足(默认)
         */
        public static final String PERM_TYPE_ALL = "1";
        /**
         * 任意一个满足即可
         */
        public static final String PERM_TYPE_ANY = "0";
    }
}
