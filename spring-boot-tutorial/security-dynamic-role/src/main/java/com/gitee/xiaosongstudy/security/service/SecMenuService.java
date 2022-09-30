package com.gitee.xiaosongstudy.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.gitee.xiaosongstudy.security.vo.SecMenuVo;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_menu(菜单项)】的数据库操作Service
* @createDate 2022-09-30 15:17:48
*/
public interface SecMenuService extends IService<SecMenu> {

    /**
     * 获取所有的菜单
     * @return
     */
    List<SecMenuVo> listAllMenus();

}
