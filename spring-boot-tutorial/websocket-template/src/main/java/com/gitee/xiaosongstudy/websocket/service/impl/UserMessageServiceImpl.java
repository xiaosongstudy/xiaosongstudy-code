package com.gitee.xiaosongstudy.websocket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.websocket.entity.UserMessage;
import org.springframework.stereotype.Service;
import com.gitee.xiaosongstudy.websocket.mapper.UserMessageMapper;
import com.gitee.xiaosongstudy.websocket.service.UserMessageService;

/**
 * @author hopeurl
 * @description 针对表【user_message(用户消息中间表)】的数据库操作Service实现
 * @createDate 2022-09-22 16:07:14
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage>
        implements UserMessageService {

}




