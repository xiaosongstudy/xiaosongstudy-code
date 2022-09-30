package com.gitee.xiaosongstudy.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.gitee.xiaosongstudy.security.mapper.SecMenuMapper;
import com.gitee.xiaosongstudy.security.service.SecMenuService;
import com.gitee.xiaosongstudy.security.vo.SecMenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_menu(菜单项)】的数据库操作Service实现
* @createDate 2022-09-30 15:17:48
*/
@Service
public class SecMenuServiceImpl extends ServiceImpl<SecMenuMapper, SecMenu>
    implements SecMenuService{

    @Resource(type = SecMenuMapper.class)
    private SecMenuMapper secMenuMapper;

    @Override
    public List<SecMenuVo> listAllMenus() {
        return secMenuMapper.listAllMenus();
    }
}




