package life.hopeurl;

import java.util.concurrent.Callable;

/**
 * 线程调度器
 *
 * @author shiping.song
 * @date 2023/5/25 21:08
 */
public class ThreadDispatcher {

    /**
     * 执行定时任务获取返回值
     *
     * @param taskCallable 待执行任务
     * @param <T>          处理结果范型
     * @return 处理结果
     * @date 2023/5/25 21:11
     */
    public <T> T execute(Callable<T> taskCallable) {
        return null;
    }
}
