package com.gitee.xiaosongstudy.sourcetrack.chapter002;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * beanFactory实现测试.<br>
 *
 * @author shiping.song
 * @date 2022/10/22 19:52
 */
public class BeanFactoryDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(C002Config.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }

    /**
     * 初始化xml容器
     * @author shiping.song
     * @date 2022/10/22 20:41
     */
    private static void initXmlApplicationContext() {
        // 初始化容器
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/bean.xml");
        System.out.println(classPathXmlApplicationContext);
        C002Bean c002Bean = classPathXmlApplicationContext.getBean(C002Bean.class);
        System.out.println(c002Bean);
    }
}
