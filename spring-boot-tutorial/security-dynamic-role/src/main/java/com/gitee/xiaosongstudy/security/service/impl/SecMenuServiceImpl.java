package com.gitee.xiaosongstudy.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.gitee.xiaosongstudy.security.mapper.SecMenuMapper;
import com.gitee.xiaosongstudy.security.service.SecMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_menu(菜单项)】的数据库操作Service实现
* @createDate 2022-10-03 17:50:47
*/
@Service
public class SecMenuServiceImpl extends ServiceImpl<SecMenuMapper, SecMenu>
    implements SecMenuService{

    @Resource(type = SecMenuMapper.class)
    private SecMenuMapper secMenuMapper;

    @Override
    public List<String> listPermsByUserId(Long id) {
        return secMenuMapper.listPermsById(id);
    }
}




