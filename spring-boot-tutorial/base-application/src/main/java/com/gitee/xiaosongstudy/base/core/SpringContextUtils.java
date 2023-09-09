package com.gitee.xiaosongstudy.base.core;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * spring 上下文工具类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/7 19:16
 */
@Service
public class SpringContextUtils implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 根据bean字节码获取对应的bean对象
     *
     * @param clz 目标字节码
     * @return 目标bean
     */
    public <T> Collection<T> getBeansOfType(Class<T> clz) {
        return applicationContext.getBeansOfType(clz).values();
    }
}
