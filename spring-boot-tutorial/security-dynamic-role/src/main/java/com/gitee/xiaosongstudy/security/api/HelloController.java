package com.gitee.xiaosongstudy.security.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "say hello";
    }

    @GetMapping("/doUpdateInfo")
    public String doUpdateInfo() {
        return "/doUpdateInfo";
    }
}
