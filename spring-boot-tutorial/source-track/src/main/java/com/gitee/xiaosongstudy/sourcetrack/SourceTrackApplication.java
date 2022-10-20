package com.gitee.xiaosongstudy.sourcetrack;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * 第一部分spring 系列地址：
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=5&spm_id_from=pageDriver&vd_source=c4d5b1024980ec17e37f8337f7a33627
 */
@SpringBootApplication
public class SourceTrackApplication {
    // CTRL + H 查看类的继承体系截图
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        ConfigurableApplicationContext context = SpringApplication.run(SourceTrackApplication.class, args);
        // beanFactory的实际类型为：org.springframework.beans.factory.support.DefaultListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

         viewSingletonObjects(beanFactory);

         i18N(context);
        // 2. 支持资源获取
        // 2.1 获取单文件
        // context.getResource("application.properties");
        // 2.2 获取多文件，使用路径匹配规则
        // context.getResources("classpath*:META-INF/spring.factories");
        // 3. 获取上下文环境
	/*	ConfigurableEnvironment environment = context.getEnvironment();
		environment.getSystemProperties().forEach((k,v)->{
			System.out.println(k+"==="+v);
		});*/
        // 4. 事件监听
        // ApplicationEvent
    }


    /**
     * 查看singletonObjects bean实例对象
     * @param beanFactory bean工厂
     * @author shiping.song
     * @date 2022/10/20 10:36
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void viewSingletonObjects(ConfigurableListableBeanFactory beanFactory) throws NoSuchFieldException, IllegalAccessException {
        // 查看DefaultListableBeanFactory的父类  DefaultSingletonBeanRegistry ，其中 singletonObjects 存放的则是bean的名称及其实例，这里使用反射获取到该内容
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        Map<String, Object> beanMap = (Map<String, Object>) singletonObjects.get(beanFactory);
        // 遍历beanMap 过滤出我们自己的bean实例
        beanMap.entrySet().stream().filter(entry->entry.getKey().startsWith("component")).forEach(System.out::println);
    }

    /**
     * 多语言支持
     * @author shiping.song
     * @date 2022/10/20 10:16
     */
    private static void i18N(ConfigurableApplicationContext context) {
        System.out.println(context.getMessage("test", null, Locale.CHINA));
        System.out.println(context.getMessage("test", null, Locale.ENGLISH));
    }

}
