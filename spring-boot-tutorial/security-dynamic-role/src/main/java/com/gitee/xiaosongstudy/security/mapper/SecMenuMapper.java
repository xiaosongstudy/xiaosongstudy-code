package com.gitee.xiaosongstudy.security.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.gitee.xiaosongstudy.security.vo.SecMenuVo;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_menu(菜单项)】的数据库操作Mapper
* @createDate 2022-09-30 15:17:48
* @Entity com.gitee.xiaosongstudy.websocket.SecMenu
*/
public interface SecMenuMapper extends BaseMapper<SecMenu> {

    /**
     * 列出所有的菜单信息
     * @return
     */
    List<SecMenuVo> listAllMenus();
}




