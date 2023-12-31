package com.gitee.xiaosongstudy.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.security.entity.SecInterface;
import com.gitee.xiaosongstudy.security.service.SecInterfaceService;
import com.gitee.xiaosongstudy.security.mapper.SecInterfaceMapper;
import com.gitee.xiaosongstudy.security.vo.SecInterfaceVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author hopeurl
* @description 针对表【sec_interface(接口表)】的数据库操作Service实现
* @createDate 2022-10-03 17:49:39
*/
@Service
public class SecInterfaceServiceImpl extends ServiceImpl<SecInterfaceMapper, SecInterface>
    implements SecInterfaceService{

    @Resource(type = SecInterfaceMapper.class)
    private SecInterfaceMapper secInterfaceMapper;

    @Override
    public List<SecInterfaceVo> listAllInterfacePermsByCondition(SecInterfaceVo condition) {
        return secInterfaceMapper.listAllInterfacePermsByCondition(condition);
    }
}




