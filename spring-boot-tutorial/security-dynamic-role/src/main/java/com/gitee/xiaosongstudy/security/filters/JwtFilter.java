package com.gitee.xiaosongstudy.security.filters;

import com.gitee.xiaosongstudy.security.constant.Globals;
import com.gitee.xiaosongstudy.security.core.JwtGenerator;
import com.gitee.xiaosongstudy.security.core.JwtParseInfo;
import com.google.common.base.Joiner;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * OncePerRequestFilter 是Spring提供的一个兼容web各个版本的 Filter
 * 本类是服务于jwt认证的过滤器
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/2 22:25
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Resource
    private JwtGenerator jwtGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("/auth") || request.getRequestURI().contains("/websocket")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Authorization: Bearer xxxxx
        // 从请求头中 Authorization 获取用户的 access_token
        String accessToken = request.getHeader(Globals.AUTHORIZATION);
        if (StringUtils.hasText(accessToken)) {
            // 去掉 "Bearer " 前缀
            accessToken = accessToken.replace(Globals.BEARER, "");
            // 校验jwt
            JwtParseInfo jpi = jwtGenerator.validateAccessJwt(accessToken);
            switch (jpi.getSignatureStatus()) {
                case correct:
                    Claims claims = jpi.getClaims();
                    List<String> authorities = claims.get(Globals.AUTHORITIES, List.class);
                    String authoritiesString = Joiner.on(",").join(authorities);

                    // 封装一个 UsernamePasswordAuthenticationToken, 因为 Security 判断能否访问资源就是看 SecurityContext 中有没有 token
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(jpi.getClaims().get(Globals.NAME), "",
                                    AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString));

                    // 将 UsernamePasswordAuthenticationToken 设置到 SecurityContext中.
                    SecurityContextHolder.getContext().setAuthentication(token);
                    filterChain.doFilter(request, response);
                case expired:
                    response.setStatus(567);
                    return;
                case tamper:  // 被篡改了
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());  // HttpStatus.UNAUTHORIZED.value() 就是401
            }

        } else {  // 没有值
            response.setStatus(HttpStatus.UNAUTHORIZED.value());  // HttpStatus.UNAUTHORIZED.value() 就是401
        }
    }
}
