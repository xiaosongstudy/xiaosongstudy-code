package com.gitee.xiaosongstudy.request.controller;

import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.request.bean.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

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

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 动态获取classpath路径
        String path = Objects.requireNonNull(RequestInvocationController.class.getClassLoader().getResource("")).getPath();
        String fileUuid = UUID.randomUUID().toString().replaceAll("-", "");
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File targetFile = new File(path.substring(1).concat(fileUuid)+suffix);
        file.transferTo(targetFile);
        return "Upload Success !";
    }


}
