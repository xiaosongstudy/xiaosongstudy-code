package com.gitee.xiaosongstudy.server.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * SpringIOC容器工具类
 */
@Slf4j
@Service("MySpringContextUtils")
public final class SpringContextUtils implements ApplicationContextAware {

    private SpringContextUtils() {
        log.info("成功初始化[IOC容器工具类]。");
    }

    /**
     * Spring上下文对象
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 按类型获取托管对象
     */
    public <T> T getBean(Class<T> clazz) {
        return SpringContextUtils.applicationContext.getBean(clazz);
    }
}