package com.gitee.xiaosongstudy.websocket.core;

import com.gitee.xiaosongstudy.websocket.vo.UserVo;
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
    private static final ThreadLocal<UserVo> USER_STORE = new ThreadLocal<>();

    private RequestStore() {

    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    public static UserVo getLoginUser() {
        return USER_STORE.get();
    }

    /**
     * 设置当前登录用户信息
     *
     * @param userVo 用户视图对象
     */
    public static void setLoginUser(UserVo userVo) {
        USER_STORE.set(userVo);
    }

    /**
     * 销毁的方法
     * @author shiping.song
     * @date 2023/4/17 0:04
     */
    public static void destroy() {
        USER_STORE.remove();
    }
}
