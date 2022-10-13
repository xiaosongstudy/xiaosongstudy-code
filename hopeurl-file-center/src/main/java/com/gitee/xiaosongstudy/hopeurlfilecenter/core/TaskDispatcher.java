package com.gitee.xiaosongstudy.hopeurlfilecenter.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 任务执行器 .<br>
 *
 * @author shiping.song
 * @date 2022/9/2 16:57
 */
@Service("myTaskExecutor")
@Slf4j
public class TaskDispatcher {

    private static final String TASK_DISPATCHER = "myTaskExecutor";

    public TaskDispatcher() {
        log.info("线程任务工具类初始化完毕~");
    }

    private final ThreadFactory namedThreadFactory = new TaskThreadFactory(TASK_DISPATCHER, true, 1);

    /**
     * 线程池服务
     */
    private final ExecutorService executeService = new ThreadPoolExecutor(10, 20, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);

    /**
     * 执行线程任务
     *
     * @param runnable 任务
     */
    public void execute(Runnable runnable) {
        executeService.execute(runnable);
    }
}
