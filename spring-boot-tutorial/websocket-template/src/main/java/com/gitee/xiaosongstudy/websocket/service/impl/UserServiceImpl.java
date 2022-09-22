package com.gitee.xiaosongstudy.websocket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.websocket.entity.User;
import com.gitee.xiaosongstudy.websocket.service.UserService;
import com.gitee.xiaosongstudy.websocket.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author hopeurl
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-09-22 16:17:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




