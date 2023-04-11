package com.gitee.xiaosongstudy.base.utils;

/**
 * 线程工具类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/6 11:42
 */
public class ThreadUtil {

    private ThreadUtil(){}

    /**
     * 通过线程id获取具体某一个线程
     * @param threadId 线程编号
     * @return 目标线程
     */
    public static Thread getThreadById(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while (group != null) {
            Thread[] threads = new Thread[(int) (group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for (int i = 0; i < count; i++) {
                if (threadId == threads[i].getId()) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }
}
