package com.gitee.xiaosongstudy.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.security.entity.SecInterfacePerm;
import com.gitee.xiaosongstudy.security.mapper.SecInterfacePermMapper;
import com.gitee.xiaosongstudy.security.service.SecInterfacePermService;
import org.springframework.stereotype.Service;

/**
* @author hopeurl
* @description 针对表【sec_interface_perm(接口权限表)】的数据库操作Service实现
* @createDate 2022-10-03 17:52:12
*/
@Service
public class SecInterfacePermServiceImpl extends ServiceImpl<SecInterfacePermMapper, SecInterfacePerm>
    implements SecInterfacePermService{
}




