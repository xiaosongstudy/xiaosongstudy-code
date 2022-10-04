package com.gitee.xiaosongstudy.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gitee.xiaosongstudy.security.entity.SecInterface;
import com.gitee.xiaosongstudy.security.vo.SecInterfaceVo;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_interface(接口表)】的数据库操作Mapper
* @createDate 2022-10-03 17:49:39
* @Entity com.gitee.xiaosongstudy.security.entity.SecInterface
*/
public interface SecInterfaceMapper extends BaseMapper<SecInterface> {


    /**
     * 过条件获取接口权限信息
     * @param condition 条件对象
     * @return 符合条件的接口权限信息
     */
    List<SecInterfaceVo> listAllInterfacePermsByCondition(SecInterfaceVo condition);

}




