package com.gitee.xiaosongstudy.exception.handler;

import com.gitee.xiaosongstudy.exception.core.*;
import com.gitee.xiaosongstudy.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常处理器 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 10:43
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        log.info("全局异常处理器初始化完成....");
    }

    /**
     * 业务异常处理
     *
     * @param businessException 业务异常
     * @return 响应结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException businessException) {
        String errorMsg = getMessage(businessException);
        log.error(errorMsg);
        return Result.failure(errorMsg);
    }

    /**
     * 业务参数异常处理
     *
     * @param businessParamException 业务参数异常处理
     * @return 响应结果
     */
    @ExceptionHandler(BusinessParamException.class)
    public Result businessParamException(BusinessParamException businessParamException) {
        String errorMsg = getMessage(businessParamException);
        log.error(errorMsg);
        return Result.failure(ResponseStatusEnum.PARAM_ERROR.getCode(), errorMsg);
    }

    /**
     * 未认证异常处理
     *
     * @param unAuthenticationException 未认证异常
     * @return 响应结果
     */
    @ExceptionHandler(UnAuthenticationException.class)
    public Result unAuthenticationExceptionHandler(UnAuthenticationException unAuthenticationException) {
        String errorMsg = getMessage(unAuthenticationException);
        log.error(errorMsg);
        return Result.failure(HttpStatus.UNAUTHORIZED.value(), errorMsg);
    }

    /**
     * 兜底异常处理器
     * @param e 异常处理器
     * @return 响应结果
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        String errorMsg = getMessage(e);
        log.error(errorMsg);
        return Result.failure(errorMsg);
    }

    /**
     * 获取异常信息 .<br>
     *
     * @param throwable 程序抛出的异常
     * @return java.lang.String
     * @author shiping.song
     * @date 2022/9/19 10:00
     */
    private String getMessage(Throwable throwable) {
        try (StringWriter sw = new StringWriter(); PrintWriter printWriter = new PrintWriter(sw)) {
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception exception) {
            log.error(ExceptionUtils.class.getSimpleName(), exception);
        }
        return String.format("【%s】：系统出现异常，请联系运营人员！", DateUtils.getLocalTime());
    }
}
