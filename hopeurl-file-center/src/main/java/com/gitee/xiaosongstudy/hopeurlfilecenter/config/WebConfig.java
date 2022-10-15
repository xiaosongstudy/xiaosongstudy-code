package com.gitee.xiaosongstudy.hopeurlfilecenter.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web context config
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 14:24
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private AppConfig appConfig;

    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        AppConfig.Security security = appConfig.getSecurity();
        registry.addMapping(security.getAllowedPath()[0])
                .allowedHeaders(security.getAllowedHeader())
                .allowedMethods(security.getAllowedMethod())
                .allowedOrigins(security.getAllowedOrigin())
                .allowedOriginPatterns("*");


    }
}
