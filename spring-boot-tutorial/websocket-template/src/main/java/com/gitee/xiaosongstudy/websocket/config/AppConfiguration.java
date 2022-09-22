package com.gitee.xiaosongstudy.websocket.config;

import lombok.Setter;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置类 .<br>
 *
 * @author shiping.song
 * @date 2022/9/22 16:31
 */
@Configuration
@Setter
public class AppConfiguration {

    /**
     * 是否开启多设备登录支持
     */
    private String multipleLogin;

    /**
     * 是否开启多设备登录支持
     * @return 配置结果
     */
    public boolean getMultipleLogin() {
        return Boolean.parseBoolean(multipleLogin);
    }
}
