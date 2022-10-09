package com.gitee.xiaosongstudy.client.controller;

import com.gitee.xiaosongstudy.sdk.constant.Globals;
import com.gitee.xiaosongstudy.sdk.vo.Invocation;
import com.gitee.xiaosongstudy.sdk.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource(type = RestTemplate.class)
    private RestTemplate restTemplate;

    @GetMapping("/listAllUsersInfo")
    public String listAllUsersInfo() {
        Invocation invocation = new Invocation();
        invocation.setClassName(Globals.USER_API);
        invocation.setMethodName(Globals.LIST_ALL_USER_INFO);
        invocation.setParamTypes(new Class[]{String.class});
        invocation.setArgs(new Object[]{"我是测试参数！"});

        // 开始调用服务端
        ResponseEntity<Result> resultResponseEntity = restTemplate.postForEntity(Globals.API_ADDRESS, invocation, Result.class);
        log.info(Objects.requireNonNull(resultResponseEntity.getBody()).toString());
        return "success";
    }

}
