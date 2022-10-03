package com.gitee.xiaosongstudy.security.filters;

import com.gitee.xiaosongstudy.security.service.SecMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 全局安全过滤器<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 21:08
 */
@Component
public class GlobalSecurityFilter implements FilterInvocationSecurityMetadataSource {

    @Resource(type = SecMenuService.class)
    private SecMenuService secMenuService;
    /**
     * 路径匹配符
     */
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (object instanceof FilterInvocation) {
            FilterInvocation filterInvocation = (FilterInvocation) object;
            // 获取请求地址
            String requestUrl = filterInvocation.getRequestUrl();
            // 获取到与请求地址相关的所有权限信息
            /*List<SecMenuVo> secMenuVos = secMenuService.listAllMenus();
            final List<String> rolesStr = new ArrayList<>();
            secMenuVos.forEach(secMenuVo -> {
                String apiPath = secMenuVo.getModulePath().concat(secMenuVo.getPath());
                // 如果路径匹配上了
                if (pathMatcher.match(apiPath, requestUrl)) {
                    // 获取角色信息
                    List<SecRole> secRoleList = secMenuVo.getSecRoleList();
                    secRoleList.forEach(secRole -> {
                        rolesStr.add(secRole.getName());
                    });
                }
            });*/
//            return rolesStr.size() > 0 ? SecurityConfig.createList(rolesStr.toArray(new String[0])) : null;
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
