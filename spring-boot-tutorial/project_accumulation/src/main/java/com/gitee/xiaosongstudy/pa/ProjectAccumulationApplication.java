package com.gitee.xiaosongstudy.pa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shiping.song
 * @date 2023/3/15 0:45
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.gitee.xiaosongstudy.pa.carService.mapper")
public class ProjectAccumulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAccumulationApplication.class, args);
    }
}
