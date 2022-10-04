package com.gitee.xiaosongstudy.security.filters;

import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.base.container.MapContainer;
import com.gitee.xiaosongstudy.security.constant.Globals;
import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.gitee.xiaosongstudy.security.vo.SecInterfaceVo;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 全局安全过滤器<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 21:08
 */
@Component
public class GlobalSecurityFilter implements FilterInvocationSecurityMetadataSource {

    @Resource(type = MapContainer.class)
    private MapContainer mapContainer;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (object instanceof FilterInvocation) {
            FilterInvocation filterInvocation = (FilterInvocation) object;
            // 获取请求地址
            String requestUrl = filterInvocation.getRequestUrl();
            if (StringUtils.hasText(requestUrl)) {
                // 从redis中获取到当前接口所需要的权限信息
                Object permsObj = mapContainer.getValue(Globals.INTERFACE_PERMS, requestUrl);
                if (null != permsObj) {
                    SecInterfaceVo secInterfaceVo = JSON.parseObject(permsObj.toString(), SecInterfaceVo.class);
                    if (null != secInterfaceVo) {
                        filterInvocation.getRequest().setAttribute(Globals.PERMS_TYPE,secInterfaceVo.getPermType());
                        List<SecMenu> permList = secInterfaceVo.getPermList();
                        return SecurityConfig.createList(permList.stream().map(SecMenu::getPerms).toArray(String[]::new));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
