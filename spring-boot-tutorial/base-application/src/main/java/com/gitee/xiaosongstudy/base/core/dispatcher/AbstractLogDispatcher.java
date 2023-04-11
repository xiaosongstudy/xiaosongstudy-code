package com.gitee.xiaosongstudy.base.core.dispatcher;

import com.gitee.xiaosongstudy.base.core.CallBackInterface;
import com.gitee.xiaosongstudy.base.core.exception.CallBackFailException;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志调度器基类.<br>
 *
 * @author shiping.song
 * @date 2023/4/2 11:04
 */
@Slf4j
public abstract class AbstractLogDispatcher<T, V, M> extends AbstractDispatcher<T, V, M> {

    /**
     * 持久化
     * note: 重写时注意考虑异常情况
     *
     * @author shiping.song
     * @date 2023/4/2 11:11
     */
    public abstract void persistence(M presistObj);

    /**
     * 回调抛出异常时可借由本方法进行异常后置处理
     *
     * @author shiping.song
     * @date 2023/4/2 11:19
     */
    public abstract void handleCallBackFail();

    @Override
    public void execute(CallBackInterface<T, V> callBackInterface) {
        // 前置校验会抛出异常，阻断请求的执行
        this.preValidate();
        try {
            callBackInterface.callBack();
        } catch (CallBackFailException e) {
            log.error(AbstractDispatcher.class.getName(), e);
            this.handleCallBackFail();
        } finally {
            // 进行日志校验时不允许抛错异常，否则会影响正常记录日志，后置校验更多的是起着警醒作用
            M logObj = this.postValidate();
            this.persistence(logObj);
        }
    }

    @Override
    public T executeAndGet(CallBackInterface<T, V> callBackInterface) {
        // 前置校验会抛出异常，阻断请求的执行
        this.preValidate();
        try {
            return callBackInterface.callBack();
        } catch (CallBackFailException e) {
            log.error(AbstractDispatcher.class.getName(), e);
            this.handleCallBackFail();
            return null;
        } finally {
            // 进行日志校验时不允许抛错异常，否则会影响正常记录日志，后置校验更多的是起着警醒作用
            M logObj = this.postValidate();
            this.persistence(logObj);
        }
    }
}
