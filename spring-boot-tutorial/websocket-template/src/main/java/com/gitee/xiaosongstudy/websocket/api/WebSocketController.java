package com.gitee.xiaosongstudy.websocket.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gitee.xiaosongstudy.exception.asserts.BusinessAssert;
import com.gitee.xiaosongstudy.exception.core.Request;
import com.gitee.xiaosongstudy.exception.core.Result;
import com.gitee.xiaosongstudy.utils.MD5Util;
import com.gitee.xiaosongstudy.websocket.entity.MessageStore;
import com.gitee.xiaosongstudy.websocket.entity.User;
import com.gitee.xiaosongstudy.websocket.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * websocket测试接口<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 0:45
 */
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @Resource
    private UserService userService;


    /**
     * 用户登录
     *
     * @param request
     * @return 处理结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody Request request) {
        User user = JSON.parseObject(request.getData(), User.class);
        BusinessAssert.notNull(user, "请求对象为空！");
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getUsername, user.getUsername());
        User one = userService.getOne(lambdaQueryWrapper);
        BusinessAssert.notNull(one, "用户名或者密码错误！");
        BusinessAssert.eq(MD5Util.removeSalt(one.getPassword()), MD5Util.encode(user.getPassword()), "用户名或者密码错误！");
        one.setPassword(null);
        return Result.success(JSON.toJSONString(one));
    }

    /**
     * 发送公告
     *
     * @param request 请求数据
     * @return 处理结果
     */
    public Result publishAnnouncement(@RequestBody Request request) {
        MessageStore messageStore = JSONObject.parseObject(request.getData(), MessageStore.class);
        BusinessAssert.notNull(messageStore, "公告内容为空！");
        return null;
    }
}
