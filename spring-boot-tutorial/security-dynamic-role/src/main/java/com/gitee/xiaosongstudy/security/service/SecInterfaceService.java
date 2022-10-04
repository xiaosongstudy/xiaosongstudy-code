package com.gitee.xiaosongstudy.security.service;

import com.gitee.xiaosongstudy.security.entity.SecInterface;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.xiaosongstudy.security.vo.SecInterfaceVo;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_interface(接口表)】的数据库操作Service
* @createDate 2022-10-03 17:49:39
*/
public interface SecInterfaceService extends IService<SecInterface> {


    /**
     * 通过条件获取接口权限信息
     * @param condition 条件对象
     * @return 所有符合条件的权限信息
     */
    List<SecInterfaceVo> listAllInterfacePermsByCondition(SecInterfaceVo condition);

}
