package com.gitee.xiaosongstudy.hopeurlfilecenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gitee.xiaosongstudy.hopeurlfilecenter.mapper")
public class HopeurlFileCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopeurlFileCenterApplication.class, args);
    }

}
