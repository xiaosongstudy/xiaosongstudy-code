package com.gitee.xiaosongstudy.websocket.vo;

import com.gitee.xiaosongstudy.websocket.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户视图对象 .<br>
 *
 * @author shiping.song
 * @date 2022/9/23 9:45
 */
@Getter
@Setter
public class UserVo extends User {
    private static final long serialVersionUID = 3426175547333914386L;

    /**
     * 用户token信息
     */
    private String accessToken;
}
