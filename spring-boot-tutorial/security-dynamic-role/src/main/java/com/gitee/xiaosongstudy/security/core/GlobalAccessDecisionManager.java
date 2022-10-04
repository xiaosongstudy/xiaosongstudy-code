package com.gitee.xiaosongstudy.security.core;

import cn.hutool.core.collection.CollectionUtil;
import com.gitee.xiaosongstudy.security.constant.FieldConstants;
import com.gitee.xiaosongstudy.security.constant.Globals;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author hopeurl
 */
@Component
public class GlobalAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication   当前登录用户信息
     * @param object           当前请求对象(FilterInvocation对象)
     * @param configAttributes FilterInvocationSecurityMetadataSource接口实现类中getAttributes方法的返回值
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (object instanceof FilterInvocation) {
            FilterInvocation filterInvocation = (FilterInvocation) object;
            // 当前接口鉴权模式
            String permsType = filterInvocation.getRequest().getAttribute(Globals.PERMS_TYPE).toString();
            // 获取当前登录用户的所有权限信息
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 用户权限
            List<String> userPerms = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            // 接口所需权限
            List<String> interfacePerms = configAttributes.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(userPerms) && CollectionUtil.isNotEmpty(interfacePerms)) {
                AtomicInteger voteCounter = new AtomicInteger(0);
                interfacePerms.forEach(interfacePerm -> {
                    if (userPerms.contains(interfacePerm)) {
                        voteCounter.addAndGet(1);
                    }
                });

                // 如果需要全符合则 voteCounter等于接口perms长度
                if (FieldConstants.SecInterface.PERM_TYPE_ALL.equals(permsType) && voteCounter.get() == interfacePerms.size()) {
                    return;
                }
                // 如果只需要满足一个条件，则 voteCounter计数器大于0即可
                if (FieldConstants.SecInterface.PERM_TYPE_ANY.equals(permsType) && voteCounter.get() > 0) {
                    return;
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
