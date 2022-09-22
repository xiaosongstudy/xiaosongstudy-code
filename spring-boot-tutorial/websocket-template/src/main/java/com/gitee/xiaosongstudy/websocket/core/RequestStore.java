package com.gitee.xiaosongstudy.websocket.core;

import com.gitee.xiaosongstudy.websocket.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求仓库 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 21:52
 */
@Slf4j
public class RequestStore {
    /**
     * 用户仓库
     */
    public static final ThreadLocal<User> USER_STORE = new ThreadLocal<>();

    private RequestStore() {

    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    public static User getLoginUser() {
        return USER_STORE.get();
    }
}
