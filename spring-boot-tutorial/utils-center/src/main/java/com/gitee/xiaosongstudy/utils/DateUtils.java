package com.gitee.xiaosongstudy.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/19 10:02
 */
public class DateUtils {
    private DateUtils() {
    }

    /**
     * 默认时间格式化：yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DEFAULT_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化本地时间  .<br>
     *
     * @param dateTimeFormatter 时间格式化格式
     * @param localDateTime     待格式化时间
     * @return java.lang.String
     * @author shiping.song
     * @date 2022/9/19 10:10
     */
    public static String formatLocalDateTime(DateTimeFormatter dateTimeFormatter, LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获取当前时间
     * @return 默认格式的当前时间
     */
    public static String getLocalTime() {
        return formatLocalDateTime(DEFAULT_TIME_FORMAT, LocalDateTime.now());
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(DEFAULT_TIME_FORMAT));
    }
}
