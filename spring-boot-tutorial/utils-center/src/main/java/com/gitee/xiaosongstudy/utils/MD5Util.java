package com.gitee.xiaosongstudy.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * md5工具类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 16:30
 */
public class MD5Util {
    /**
     * 加盐位置
     */
    private static final int[] SALTS = {3, 8, 16, 22, 26};

    private MD5Util() {
    }

    /**
     * 数据加密
     *
     * @param str 待加密数据
     * @return 加密后的密码
     */
    public static String encode(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] bb = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, bb).toString(32);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * md5密码加盐
     *
     * @param md5Str md5字符串
     * @return 加密之后的结果
     */
    public static String addSalt(String md5Str) {
        StringBuilder sb = new StringBuilder(md5Str);
        for (int n = SALTS.length - 1; n >= 0; n--) {
            int r = (int) (Math.random() * 10);
            // 插入盐值
            sb.insert(SALTS[n], r);
        }
        return sb.toString();
    }

    /**
     * 移除盐值
     *
     * @param md5Str md5加密后数据
     * @return 原md5数据
     */
    public static String removeSalt(String md5Str) {
        StringBuilder sb = new StringBuilder(md5Str);
        for (int index : SALTS) {
            sb.deleteCharAt(index);
        }
        return sb.toString();
    }

   /* public static void main(String[] args) {
        String password = "64p8913h835p4e10rj02gj9nn0b14r4";
        System.out.println(removeSalt(password).equals(encode("1")));
    }*/
}
