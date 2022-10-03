package com.gitee.xiaosongstudy.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 应用配置类 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/2 22:55
 */
@Configuration
@ConfigurationProperties(prefix = "application")
@Setter
@Getter
public class AppConfiguration {
    /**
     * 安全相关配置
     */
    private Security security = new Security();

    /**
     * 安全配置类
     */
    @Getter
    @Setter
    public static class Security {
        private List<String> allowedOrigin;
        private List<String> allowedHeader;
        private List<String> allowedMethod;
        private List<String> allowedPath;
        private List<String> antMatchers;
    }
}
