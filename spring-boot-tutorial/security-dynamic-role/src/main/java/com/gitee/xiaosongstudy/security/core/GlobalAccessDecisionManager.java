package com.gitee.xiaosongstudy.security.core;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author hopeurl
 */
@Component
public class GlobalAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        /*
         * authentication： 当前登录用户信息
         * o：当前请求对象(FilterInvocation对象)
         *collection：FilterInvocationSecurityMetadataSource接口实现类中getAttributes方法的返回值
         * */
        for (ConfigAttribute attribute : configAttributes ) {
            if ("ROLE_login".equals(attribute.getAttribute())) {      //路径不在数据库配置范围内，返回标志ROLE_login
                if (authentication instanceof AnonymousAuthenticationToken) { //用户未登录
                    throw new AccessDeniedException("非法请求!");
                } else {
                    return;      //用户已经登录 无需判断 方法到此结束
                }
            }
            //获取当前登录用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(attribute.getAttribute())) {
                    return;      //当前登录用户具备所需的角色 则无需判断
                }
            }
        }
        throw new AccessDeniedException("非法请求!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
