package com.gitee.xiaosongstudy.fcs.config;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件中心配置项 .<br>
 *
 * @author shiping.song
 * @date 2022/10/15 16:18
 */
@Data
public final class FileCenterConfig implements Serializable {
    private static final long serialVersionUID = 4365932625909267064L;

    /**
     *  服务器地址
     */
    private String serverAddress;

    /**
     * service api地址
     */
    private String serviceApiUrl;

    /**
     * 登录账号
     */
    private String accessKey;

    /**
     * 登录密码
     */
    private String accessSecret;

    /**
     * 是否开启加密传输
     */
    private Boolean security;
}
