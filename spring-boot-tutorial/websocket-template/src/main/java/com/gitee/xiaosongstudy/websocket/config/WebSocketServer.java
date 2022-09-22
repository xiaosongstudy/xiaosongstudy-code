package com.gitee.xiaosongstudy.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 宋世平 email:2453332538@qq.com 2021/11/3 0003
 * websocket服务器监听
 **/
@Component
@ServerEndpoint("/client/{userId}")
public class WebSocketServer {

    private static final ConcurrentHashMap<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 前端发送请求建立websocket连接，执行此方法
     *
     * @param userId
     */
    @OnOpen
    public void open(@PathParam(("userId")) String userId, Session session) {
        SESSION_MAP.put(userId, session);
    }

    /**
     * 前端关闭页面或者主动关闭websocket连接，都会执行本方法
     *
     * @param userId
     */
    @OnClose
    public void close(@PathParam("userId") String userId) {
        SESSION_MAP.remove(userId);
    }

    /**
     * 向指定客户端发送信息
     *
     * @param userId 用户id编号
     * @param msg 待发送信息
     */
    public static void sendMsg(String userId, String msg) {
        Session session = SESSION_MAP.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发信息
     * @param message 待发送信息
     */
    public static void sendMessMessage(String message) {
        Collection<Session> sessions = SESSION_MAP.values();
        sessions.forEach(session -> {
            if (null != session && session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
