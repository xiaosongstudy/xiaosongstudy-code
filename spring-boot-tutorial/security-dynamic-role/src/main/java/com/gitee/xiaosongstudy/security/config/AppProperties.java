package com.gitee.xiaosongstudy.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 全局应用配置文件<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/2 19:25
 */
@ConfigurationProperties(prefix = "app")
@Configuration
@PropertySource("classpath:app-config.properties")
@Getter
@Setter
public class AppProperties {

    private Jwt jwt = new Jwt();


    /**
     * jwt相关配置
     */
    @Setter
    @Getter
    public static class Jwt {
        private Long accessTokenExpire;
        private Long refreshTokenExpire;
        private String accessKey;
        private String refreshKey;
    }
}
