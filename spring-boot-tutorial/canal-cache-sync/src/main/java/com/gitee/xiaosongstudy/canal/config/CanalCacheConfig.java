package com.gitee.xiaosongstudy.canal.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class CanalCacheConfig {
    /**
     * canal配置
     */
    private final Canal canal = new Canal();

    @Getter
    @Setter
    public static class Canal {
        private String port;
        private String batchSize;
        private String subscribe;
        private String interval;
    }

    public CanalCacheConfig() {
        log.info("应用核心配置类初始化完成");
    }

}
