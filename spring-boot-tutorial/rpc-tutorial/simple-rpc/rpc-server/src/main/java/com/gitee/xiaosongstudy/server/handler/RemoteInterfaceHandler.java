package com.gitee.xiaosongstudy.server.handler;

import com.gitee.xiaosongstudy.sdk.vo.Invocation;
import com.gitee.xiaosongstudy.sdk.vo.Result;
import com.gitee.xiaosongstudy.server.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 远程接口处理器
 */
@Component
@Slf4j
public class RemoteInterfaceHandler {
    @Resource(type = SpringContextUtils.class)
    private SpringContextUtils springContextUtils;

    /**
     * 解析请求数据并响应执行结果
     *
     * @param invocation 请求数据
     * @return 执行响应结果
     */
    public Result resolve(Invocation invocation) {
        Result result = new Result();
        try {
            Class<?> targetClz = Class.forName(invocation.getClassName());
            // 从bean容器中获取到对应的bean对象
            Object targetBean = springContextUtils.getBean(targetClz);
            if (null != targetBean) {
                String targetMethodName = invocation.getMethodName();
                // 获取目标执行方法
                Method targetMethod = targetClz.getMethod(targetMethodName, invocation.getParamTypes());
                // 执行目标方法
                Object invoke = targetMethod.invoke(targetBean, invocation.getArgs());
                result.setResultParamTypes(new Class[]{invoke.getClass()});
                result.setResultArgs(new Object[]{invoke});
            } else {
                throw new IllegalArgumentException("非法请求参数！");
            }
        } catch (Exception e) {
            log.error(RemoteInterfaceHandler.class.getSimpleName(), e);
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setErrorMsg(getMessage(e));
        }
        return result;
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
            log.error(RemoteInterfaceHandler.class.getSimpleName(), exception);
        }
        return String.format("【%s】：系统出现异常，请联系运营人员！", LocalDateTime.now());
    }
}
