package com.gitee.xiaosongstudy.security.service;

import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_menu(菜单项)】的数据库操作Service
* @createDate 2022-10-03 17:50:47
*/
public interface SecMenuService extends IService<SecMenu> {

    /**
     * 通过用户编号获取到用户的所有权限信息
     * @param id 用户编号
     * @return
     */
    List<String> listPermsByUserId(Long id);
}
