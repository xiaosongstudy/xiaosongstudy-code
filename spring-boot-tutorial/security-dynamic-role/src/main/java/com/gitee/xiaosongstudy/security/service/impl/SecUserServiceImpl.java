package com.gitee.xiaosongstudy.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.base.asserts.Asserts;
import com.gitee.xiaosongstudy.security.constant.Globals;
import com.gitee.xiaosongstudy.security.core.JwtGenerator;
import com.gitee.xiaosongstudy.security.entity.SecUser;
import com.gitee.xiaosongstudy.security.mapper.SecUserMapper;
import com.gitee.xiaosongstudy.security.service.SecMenuService;
import com.gitee.xiaosongstudy.security.service.SecUserService;
import com.google.common.base.Joiner;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hopeurl
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-09-22 16:17:08
 */
@Service
public class SecUserServiceImpl extends ServiceImpl<SecUserMapper, SecUser>
        implements SecUserService {


    @Resource(type = SecMenuService.class)
    private SecMenuService secMenuService;

    @Resource(type = SecUserMapper.class)
    private SecUserMapper secUserMapper;

    @Resource(type = PasswordEncoder.class)
    private PasswordEncoder passwordEncoder;

    @Resource(type = JwtGenerator.class)
    private JwtGenerator jwtGenerator;

    @Override
    public Map<String, Object> login(SecUser secUser) {
        LambdaQueryWrapper<SecUser> nameQueryWrapper = Wrappers.lambdaQuery(SecUser.class).eq(SecUser::getUsername, secUser.getUsername());
        SecUser secUserInfo = secUserMapper.selectOne(nameQueryWrapper);
        Asserts.notNull(secUserInfo, "用户名或者密码错误！");
        // 如果匹配不上就报错
        Asserts.isTure(passwordEncoder.matches(secUser.getPassword(), secUserInfo.getPassword()), "用户名或者密码错误！");
        // 获取用户的权限信息
        List<String> perms = secMenuService.listPermsByUserId(secUserInfo.getId());
        String permString = Joiner.on(",").join(perms);
        final Map<String, Object> tokens = new HashMap<>();
        UserDetails userDetails = new User(secUser.getUsername(), "", AuthorityUtils.commaSeparatedStringToAuthorityList(permString));
        tokens.put(Globals.ACCESS_TOKEN, jwtGenerator.createAccessJwt(userDetails));
        tokens.put(Globals.REFRESH_TOKEN, jwtGenerator.createRefreshJwt(userDetails));
        return tokens;
    }
}




