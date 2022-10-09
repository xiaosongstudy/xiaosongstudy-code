package com.gitee.xiaosongstudy.server.local;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author hopeurl
 */
@Service
public class UserApi {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String appName;

    public String listAllUserInfo(String param) {
        return String.format("当前调用的服务是：[localhost:%s%s]:%s，参数为：%s", port, appName, "listAllUserInfo",param);
    }
}
