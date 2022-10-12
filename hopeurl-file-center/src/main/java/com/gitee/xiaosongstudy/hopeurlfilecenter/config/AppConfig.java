package com.gitee.xiaosongstudy.hopeurlfilecenter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 全局应用配置
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 14:24
 */
@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class AppConfig {

    /**
     * 安全相关配置
     */
    private Security security = new Security();

    /**
     * 文件配置
     */
    private File file = new File();

    /**
     * 安全配置类
     */
    @Getter
    @Setter
    public static class Security {
        private String[] allowedOrigin;
        private String[] allowedHeader;
        private String[] allowedMethod;
        private String[] allowedPath;
        private String[] antMatchers;
    }


    @Getter
    @Setter
    public static class File {
        private String savePath;
    }

}
