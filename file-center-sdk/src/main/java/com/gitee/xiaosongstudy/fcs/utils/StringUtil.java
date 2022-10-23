package com.gitee.xiaosongstudy.fcs.utils;

/**
 * 字符串工具类
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 15:55
 */
public class StringUtil {

    private StringUtil(){}

    public static boolean isNotEmpty(String str) {
        return null != str && str.trim().length() > 0;
    }

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }
}
