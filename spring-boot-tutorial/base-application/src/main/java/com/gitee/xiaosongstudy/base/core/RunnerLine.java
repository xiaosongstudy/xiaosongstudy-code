package com.gitee.xiaosongstudy.base.core;

/**
 * 初始化任务执行规范 .<br>
 *
 * @author shiping.song
 * @date 2022/9/7 19:02
 */
public interface RunnerLine {
    /**
     * 执行顺序
     *
     * @return 返回顺序值
     */
    int order();

    /**
     * 开始执行
     */
    void run();

    /**
     * 是否同步默认是同步的
     * @return 结果是否同步
     */
    default boolean async() {
        return true;
    }
}
