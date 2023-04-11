package com.gitee.xiaosongstudy.base.core.dispatcher;

import com.gitee.xiaosongstudy.base.core.CallBackInterface;

/**
 * 调度器基类.<br>
 * 三个泛型的含义如下：
 * <ul>
 *     <li>T： 回调接口返回的类型</li>
 *      <li>V： 回调接口入参的类型</li>
 *       <li>M： 辅助泛型</li>
 * </ul>
 *
 * @author shiping.song
 * @date 2023/4/2 10:54
 */
public abstract class AbstractDispatcher<T, V, M> {

    /**
     * 前置校验
     *
     * @return 校验结果
     * @author shiping.song
     * @date 2023/4/2 10:55
     */
    public abstract M preValidate();

    /**
     * 后置校验
     *
     * @return 校验结果
     * @author shiping.song
     * @date 2023/4/2 10:57
     */
    public abstract M postValidate();

    /**
     * 执行调度，无返回值
     *
     * @param callBackInterface 待回调函数
     * @author shiping.song
     * @date 2023/4/2 11:01
     */
    public abstract void execute(CallBackInterface<T, V> callBackInterface);

    /**
     * 执行调度，有回调值
     *
     * @param callBackInterface 待回调函数
     * @return 调度结果
     * @author shiping.song
     * @date 2023/4/2 11:01
     */
    public abstract T executeAndGet(CallBackInterface<T, V> callBackInterface);

}
