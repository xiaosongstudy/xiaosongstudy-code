package com.gitee.xiaosongstudy.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 基于Spring Bott Security动态权限配置应用核心启动类
 * </p>
 *
 * @author 宋世平 email:2453332538@qq.com 2022/9/30
 **/
@SpringBootApplication(scanBasePackages="com.gitee.xiaosongstudy")
@MapperScan("com.gitee.xiaosongstudy.security.mapper")
public class SecurityDynamicRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDynamicRoleApplication.class, args);
    }
}
