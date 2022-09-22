package com.gitee.xiaosongstudy.websocket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.websocket.entity.MessageStore;
import org.springframework.stereotype.Service;
import com.gitee.xiaosongstudy.websocket.mapper.MessageStoreMapper;
import com.gitee.xiaosongstudy.websocket.service.MessageStoreService;

/**
* @author hopeurl
* @description 针对表【message_store(消息仓库)】的数据库操作Service实现
* @createDate 2022-09-22 16:06:37
*/
@Service
public class MessageStoreServiceImpl extends ServiceImpl<MessageStoreMapper, MessageStore>
    implements MessageStoreService{

}




