package com.gitee.xiaosongstudy.security.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello security!";
    }
    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }
    @GetMapping("/db/hello")
    public String db(){
        return "hello db!";
    }
    @GetMapping("/user/hello")
    public String user(){
        return "hello user!";
    }
}
