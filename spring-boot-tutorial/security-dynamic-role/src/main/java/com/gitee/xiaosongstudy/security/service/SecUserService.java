package com.gitee.xiaosongstudy.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.xiaosongstudy.security.entity.SecUser;

import java.util.Map;

/**
 * @author hopeurl
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2022-09-22 16:17:08
 */
public interface SecUserService extends IService<SecUser> {

    /**
     * 用户登录
     * @param secUser 用户登录信息
     * @return 用户登录结果
     */
    Map<String,Object> login(SecUser secUser);
}
