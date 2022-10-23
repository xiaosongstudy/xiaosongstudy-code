package com.gitee.xiaosongstudy.sourcetrack.chapter002;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * c002config配置.<br>
 *
 * @author shiping.song
 * @date 2022/10/22 20:56
 */
@Configuration
@ComponentScan(basePackages = {"com.gitee.xiaosongstudy.sourcetrack.chapter002"})
public class C002Config {

    @Resource(type = C002Bean.class)
    private C002Bean c002Bean;

    public C002Config() {
        System.out.println("C002Config 开始初始化....");
    }

    @Autowired
    public void setC002Bean02(C002Bean02 c002Bean02) {
        System.out.println("setC002Bean02注入成功！");
    }

    @Bean
    public C002Bean02 c002Bean02() {
        return new C002Bean02("c002Bean02");
    }
}
