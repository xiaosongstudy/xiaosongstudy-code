package com.gitee.xiaosongstudy.security.api;

import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.base.asserts.BusinessAssert;
import com.gitee.xiaosongstudy.base.core.Request;
import com.gitee.xiaosongstudy.base.core.Result;
import com.gitee.xiaosongstudy.security.entity.SecUser;
import com.gitee.xiaosongstudy.security.service.SecUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限接口 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/3 1:11
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource(type = SecUserService.class)
    private SecUserService secUserService;

    /**
     * 登录
     * @param request 请求数据
     * @return 处理结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody Request request) {
        SecUser secUser = JSON.parseObject(request.getData(), SecUser.class);
        BusinessAssert.notNull(secUser, "用户信息为空！");
        BusinessAssert.notNull(secUser.getUsername(), "用户名为空！");
        BusinessAssert.notNull(secUser.getPassword(), "密码为空！");
        return Result.success(JSON.toJSONString(secUserService.login(secUser)));
    }
}
