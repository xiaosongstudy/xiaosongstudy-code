package com.gitee.xiaosongstudy.websocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * websocket案例核心启动类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/21 17:31
 */
@SpringBootApplication(scanBasePackages="com.gitee.xiaosongstudy")
@MapperScan("com.gitee.xiaosongstudy.websocket.mapper")
@ServletComponentScan("com.gitee.xiaosongstudy")
public class WebSocketTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketTemplateApplication.class,args);
    }
}
