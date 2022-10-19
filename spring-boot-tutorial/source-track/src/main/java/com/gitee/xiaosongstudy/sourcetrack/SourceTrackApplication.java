package com.gitee.xiaosongstudy.sourcetrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class SourceTrackApplication {
	// CTRL + H 查看类的继承体系截图
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(SourceTrackApplication.class, args);

		// 1. ApplicationContext支持多语言
		// context.getMessage()
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

}
