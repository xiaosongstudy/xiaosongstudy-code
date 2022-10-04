package com.gitee.xiaosongstudy.security.mapper;

import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_menu(菜单项)】的数据库操作Mapper
* @createDate 2022-10-03 17:50:47
* @Entity com.gitee.xiaosongstudy.security.entity.SecMenu
*/
public interface SecMenuMapper extends BaseMapper<SecMenu> {

    /**
     * 通过用户编号获取用户权限信息
     * @param userId 用户编号
     * @return 指定用户的权限信息
     */
    List<String> listPermsById(@Param("userId") Long userId);

}




