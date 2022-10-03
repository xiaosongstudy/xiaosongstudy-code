package com.gitee.xiaosongstudy.base.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * runner 执行接口管理器 .<br>
 *
 * @author shiping.song
 * @date 2022/9/7 18:23
 */
@Component
@Slf4j
public class RunnerManager implements CommandLineRunner {

    @Resource
    private SpringContextUtils springContextUtils;

    @Resource(name = "myTaskExecutor")
    private TaskDispatcher taskDispatcher;

    @Override
    public void run(String... args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("开始初始化系统环境");
        Collection<RunnerLine> beansOfType = springContextUtils.getBeansOfType(RunnerLine.class);
        if (null != beansOfType && !CollectionUtils.isEmpty(beansOfType)) {
            List<RunnerLine> runnerLineList = beansOfType.stream().sorted(Comparator.comparing(RunnerLine::order)).collect(Collectors.toList());
            runnerLineList.forEach(task -> {
                if (task.async()) {
                    task.run();
                } else {
                    taskDispatcher.execute(task::run);
                }
            });
        }
        stopWatch.stop();
        log.info("系统初始化完成....项目启动成功，总共耗时：{} ms",stopWatch.getTotalTimeMillis());
    }
}