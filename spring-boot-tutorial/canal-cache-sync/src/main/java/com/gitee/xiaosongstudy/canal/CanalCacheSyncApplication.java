package com.gitee.xiaosongstudy.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用canal实现mysql数据增量同步 核心启动类
 */
@SpringBootApplication
public class CanalCacheSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalCacheSyncApplication.class, args);
    }
}
