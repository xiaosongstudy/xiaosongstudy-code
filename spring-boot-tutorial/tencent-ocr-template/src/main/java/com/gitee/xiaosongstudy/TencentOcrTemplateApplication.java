package com.gitee.xiaosongstudy;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 腾讯云ocr操作模板启动类.<br>
 *
 * @author shiping.song
 * @date 2023/4/4 19:58
 */
@SpringBootApplication
public class TencentOcrTemplateApplication {

    public static void main(String[] args) {
//        System.getenv().forEach((k, v) -> {
//            System.out.println(k);
//            System.out.println(v);
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        });
        System.out.println(System.getenv("tencentOcrAppSecretId"));

//        SpringApplication.run(TencentOcrTemplateApplication.class, args);
    }
}
