package com.gitee.xiaosongstudy.websocket.api;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gitee.xiaosongstudy.exception.asserts.BusinessAssert;
import com.gitee.xiaosongstudy.exception.core.Request;
import com.gitee.xiaosongstudy.exception.core.Result;
import com.gitee.xiaosongstudy.utils.MD5Util;
import com.gitee.xiaosongstudy.websocket.constant.Flag;
import com.gitee.xiaosongstudy.websocket.constant.Globals;
import com.gitee.xiaosongstudy.websocket.container.StringContainer;
import com.gitee.xiaosongstudy.websocket.core.WebSocketServer;
import com.gitee.xiaosongstudy.websocket.entity.MessageStore;
import com.gitee.xiaosongstudy.websocket.entity.User;
import com.gitee.xiaosongstudy.websocket.entity.UserMessage;
import com.gitee.xiaosongstudy.websocket.service.MessageStoreService;
import com.gitee.xiaosongstudy.websocket.service.UserMessageService;
import com.gitee.xiaosongstudy.websocket.service.UserService;
import com.gitee.xiaosongstudy.websocket.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * websocket测试接口<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 0:45
 */
@RestController
@RequestMapping("/websocket")
@CrossOrigin("*")
public class WebSocketController {

    @Resource
    private UserService userService;

    @Resource
    private MessageStoreService messageStoreService;

    @Resource
    private UserMessageService usermessageService;

    @Resource
    private StringContainer stringContainer;

    @Resource
    private WebSocketServer webSocketServer;


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
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setPassword(null);
        // 生成token
        String accessToken = UUID.fastUUID().toString();
        userVo.setAccessToken(accessToken);
        // 将用户信息存入redis中
        stringContainer.setValue(Globals.TOKEN_CACHE_PREFIX + accessToken,JSON.toJSONString(userVo),30L, TimeUnit.MINUTES);
        return Result.success(JSON.toJSONString(userVo));
    }

    /**
     * 给指定用户发送信息
     * @param request 请求对象
     * @param userId 用户编号
     * @return
     */
    @PostMapping("/publishAnnouncement/{userId}")
    public Result publishAnnouncement(@RequestBody Request request, @PathVariable Long userId) {
        MessageStore messageStore = JSONObject.parseObject(request.getData(), MessageStore.class);
        BusinessAssert.notNull(messageStore, "公告内容为空！");
        // 开始推送信息
        // 保存消息
        messageStoreService.save(messageStore);
        BusinessAssert.notNull(messageStore.getId(), "消息编号为空！");
        // 建立用户消息关联
        UserMessage userMessage = new UserMessage();
        userMessage.setUserId(userId);
        userMessage.setPushFlag(Flag.FALSE);
        userMessage.setMessageId(messageStore.getId());
        // 存储用户消息关联信息
        usermessageService.save(userMessage);
        webSocketServer.sendMsg(userId,messageStore);
        return Result.success();
    }
}
