package com.gitee.xiaosongstudy.request.controller;

import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.request.bean.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 辅助请求示例代码
 */
@RestController
@RequestMapping("/request")
public class RequestInvocationController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello world!";
    }

    @GetMapping("/getJsonStr")
    public String getJsonStr() {
        User user = User.builder().id(20221010).username("张三").birthday(LocalDate.now()).build();
        return JSON.toJSONString(user);
    }

    @GetMapping("/param")
    public String param(Integer id, String username, LocalDate birthday) {
        return JSON.toJSONString(User.builder().id(id).username(username).birthday(birthday).build());
    }

    @GetMapping("/paramWithEntity")
    public String paramWithEntity(User user) {
        return JSON.toJSONString(user);
    }

    @PostMapping("/postStr")
    public void postStr(String param) {
        System.out.println("请求成功..."+param);
    }

    @PostMapping("/postWithEntity")
    public String postStr(@RequestBody User user) {
        return user.toString();
    }


}
