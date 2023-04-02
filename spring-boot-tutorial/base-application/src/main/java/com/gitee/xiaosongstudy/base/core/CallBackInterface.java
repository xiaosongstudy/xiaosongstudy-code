package com.gitee.xiaosongstudy.base.core;

import com.gitee.xiaosongstudy.base.core.exception.CallBackFailException;

/**
 * 辅助回调接口.<br>
 *
 * @author shiping.song
 * @date 2023/3/30 9:22
 */
@FunctionalInterface
public interface CallBackInterface<T, V> {

    /**
     * 待回调函数
     *
     * @return 回调执行结果
     * @throws CallBackFailException 回调失败异常
     * @author shiping.song
     * @date 2023/3/30 9:25
     */
    T callBack() throws CallBackFailException;

    /**
     * 有入参的回调
     *
     * @param params 回调参数
     * @return 回调结果
     * @throws CallBackFailException 回调失败异常
     * @author shiping.song
     * @date 2023/3/30 9:27
     */
    default T callBack(V params) throws CallBackFailException {
        return null;
    }
}
