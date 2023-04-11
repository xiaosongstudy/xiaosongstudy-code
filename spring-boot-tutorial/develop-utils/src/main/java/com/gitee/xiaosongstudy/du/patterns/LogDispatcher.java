package com.gitee.xiaosongstudy.du.patterns;

import java.util.regex.Pattern;

/**
 * 日志调度器
 * @author shiping.song
 * @date 2023/4/11 22:46
 * @since 1.0.0
 */
public class LogDispatcher {

    /**
     * 用户ip正则
     */
    private static final Pattern USER_IP = Pattern.compile("");

    /**
     * 通过字符串日志内容构建日志模型
     * @param logStr 字符串日志
     * @return 日志数据模型
     * @author shiping.song
     * @date 2023/4/11 22:47
     */
    public static LogModel build(String logStr){
        return null;
    }

}
