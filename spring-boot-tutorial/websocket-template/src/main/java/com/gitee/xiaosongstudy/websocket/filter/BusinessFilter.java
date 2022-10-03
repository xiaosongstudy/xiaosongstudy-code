package com.gitee.xiaosongstudy.websocket.filter;


import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.base.core.UnAuthenticationException;
import com.gitee.xiaosongstudy.websocket.constant.Globals;
import com.gitee.xiaosongstudy.base.container.StringContainer;
import com.gitee.xiaosongstudy.websocket.core.RequestStore;
import com.gitee.xiaosongstudy.websocket.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 业务过滤器 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 21:57
 */
@WebFilter("/**")
@Order(1)
@Slf4j
public class BusinessFilter implements Filter {
    public BusinessFilter() {
        log.info("全局拦截器初始化完成....");
    }

    @Resource(type = StringContainer.class)
    private StringContainer stringContainer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 如果是想要登录则放过
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUrl = httpServletRequest.getRequestURL().toString();
        if (requestUrl.contains(Globals.API_LOGIN)) {
            chain.doFilter(request,response);
        } else {
            // 如果不是登录请求则需要校验请求中是否携带用户token
            String accessToken = request.getParameter(Globals.ACCESS_TOKEN);
            if (StringUtils.hasText(accessToken)) {
                String userVoJson = stringContainer.getValue(Globals.TOKEN_CACHE_PREFIX + accessToken);
                if (StringUtils.hasText(userVoJson)) {
                    UserVo userVo = JSON.parseObject(userVoJson, UserVo.class);
                    RequestStore.setLoginUser(userVo);
                    chain.doFilter(request,response);
                } else {
                    throw new UnAuthenticationException();
                }
            } else {
                throw new UnAuthenticationException();
            }
        }
    }
}
