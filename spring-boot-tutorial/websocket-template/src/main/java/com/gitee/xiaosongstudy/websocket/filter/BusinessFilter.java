package com.gitee.xiaosongstudy.websocket.filter;

import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.exception.core.UnAuthenticationException;
import com.gitee.xiaosongstudy.websocket.constant.Globals;
import com.gitee.xiaosongstudy.websocket.container.StringContainer;
import com.gitee.xiaosongstudy.websocket.core.RequestStore;
import com.gitee.xiaosongstudy.websocket.vo.UserVo;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 业务过滤器 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 21:57
 */
@WebFilter("/**")
@Order(1)
@ServletComponentScan
public class BusinessFilter implements Filter {

    @Resource(type = StringContainer.class)
    private StringContainer stringContainer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
