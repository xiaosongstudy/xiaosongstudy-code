package com.gitee.xiaosongstudy.hopeurlfilecenter.handler;


import com.gitee.xiaosongstudy.fcs.common.ResponseStatusEnum;
import com.gitee.xiaosongstudy.fcs.model.Result;
import com.gitee.xiaosongstudy.fcs.exception.BusinessException;
import com.gitee.xiaosongstudy.fcs.exception.BusinessParamException;
import com.gitee.xiaosongstudy.fcs.exception.UnAuthenticationException;
import com.gitee.xiaosongstudy.hopeurlfilecenter.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        String errorMsg = ExceptionUtil.getMessage(businessException);
        log.error(errorMsg);
        return Result.failure(businessException.getMessage());
    }

    /**
     * 业务参数异常处理
     *
     * @param businessParamException 业务参数异常处理
     * @return 响应结果
     */
    @ExceptionHandler(BusinessParamException.class)
    public Result businessParamException(BusinessParamException businessParamException) {
        String errorMsg = ExceptionUtil.getMessage(businessParamException);
        log.error(errorMsg);
        return Result.failure(ResponseStatusEnum.PARAM_ERROR.getCode(), businessParamException.getMessage());
    }

    /**
     * 未认证异常处理
     *
     * @param unAuthenticationException 未认证异常
     * @return 响应结果
     */
    @ExceptionHandler(UnAuthenticationException.class)
    public Result unAuthenticationExceptionHandler(UnAuthenticationException unAuthenticationException) {
        String errorMsg = ExceptionUtil.getMessage(unAuthenticationException);
        log.error(errorMsg);
        return Result.failure(HttpStatus.UNAUTHORIZED.value(), unAuthenticationException.getMessage());
    }

    /**
     * 兜底异常处理器
     * @param e 异常处理器
     * @return 响应结果
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        String errorMsg = ExceptionUtil.getMessage(e);
        log.error(errorMsg);
        return Result.failure(e.getMessage());
    }
}
