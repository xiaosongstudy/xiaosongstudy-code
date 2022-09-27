package com.gitee.xiaosongstudy.utils;

import cn.hutool.log.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/27 10:19
 */
public class ExceptionUtil {

    private static final Log log = Log.get(ExceptionUtil.class);

    private ExceptionUtil() {}


    /**
     * 获取异常信息 .<br>
     *
     * @param throwable 程序抛出的异常
     * @return java.lang.String
     * @author shiping.song
     * @date 2022/9/19 10:00
     */
    public static String getMessage(Throwable throwable) {
        try (StringWriter sw = new StringWriter(); PrintWriter printWriter = new PrintWriter(sw)) {
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception exception) {
            log.error(ExceptionUtil.class.getSimpleName(), exception);
        }
        return String.format("【%s】：系统出现异常，请联系运营人员！", DateUtils.getLocalTime());
    }
}
