package com.gitee.xiaosongstudy.websocket.core;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gitee.xiaosongstudy.websocket.constant.Flag;
import com.gitee.xiaosongstudy.websocket.entity.MessageStore;
import com.gitee.xiaosongstudy.websocket.entity.UserMessage;
import com.gitee.xiaosongstudy.websocket.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 宋世平 email:2453332538@qq.com 2021/11/3 0003
 * websocket服务器监听
 **/
@Component
@ServerEndpoint("/client/{userId}")
@Slf4j
public class WebSocketServer {

    @Resource(type = UserMessageService.class)
    private UserMessageService userMessageService;

    private static final ConcurrentHashMap<Long, Session> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 前端发送请求建立websocket连接，执行此方法
     *
     * @param userId
     */
    @OnOpen
    public void open(@PathParam(("userId")) Long userId, Session session) {
        SESSION_MAP.put(userId, session);
    }

    /**
     * 前端关闭页面或者主动关闭websocket连接，都会执行本方法
     *
     * @param userId
     */
    @OnClose
    public void close(@PathParam("userId") Long userId) {
        SESSION_MAP.remove(userId);
    }

    /**
     * 向指定客户端发送信息
     *
     * @param userId 用户id编号
     * @param msg    待发送信息
     */
    public void sendMsg(Long userId, MessageStore msg) {
        Session session = SESSION_MAP.get(userId);
        if (session != null && session.isOpen()) {
            UserMessage userMessage = new UserMessage();
            try {
                session.getBasicRemote().sendText(JSON.toJSONString(msg));
                userMessage.setMessageId(msg.getId());
                userMessage.setUserId(userId);
                LambdaQueryWrapper<UserMessage> queryWrapper = Wrappers.lambdaQuery(UserMessage.class)
                        .eq(UserMessage::getMessageId, userMessage.getMessageId())
                        .eq(UserMessage::getUserId, userMessage.getUserId());
                userMessageService.update(userMessage, queryWrapper);
            } catch (IOException e) {
                log.error(WebSocketServer.class.getSimpleName(), e);
            }
        }
    }

    /**
     * 群发信息
     *
     * @param message 待发送信息
     */
    public void sendMessMessage(MessageStore message) {
        Collection<Session> sessions = SESSION_MAP.values();
        final List<UserMessage> userMessages = new ArrayList<>();
        sessions.forEach(session -> {
            if (null != session && session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(message));
                    // 发送成功后记录当前用户编号、消息编号，以便可以更新消息的状态
                    UserMessage userMessage = new UserMessage();
                    userMessage.setMessageId(message.getId());
                    userMessage.setUserId(Long.parseLong(session.getId()));
                    userMessage.setPushFlag(Flag.TRUE);
                    userMessages.add(userMessage);
                } catch (IOException e) {
                    log.error(WebSocketServer.class.getSimpleName(), e);
                }
            }
        });
        // 发送完成后开始更新消息状态
        if (CollectionUtil.isNotEmpty(userMessages)) {
            userMessageService.saveOrUpdateBatch(userMessages);
        }
    }
}
