package com.gitee.xiaosongstudy.security.config;

import com.gitee.xiaosongstudy.security.constant.Globals;
import com.gitee.xiaosongstudy.security.core.GlobalAccessDecisionManager;
import com.gitee.xiaosongstudy.security.filters.GlobalSecurityFilter;
import com.gitee.xiaosongstudy.security.filters.JwtFilter;
import com.google.common.base.Joiner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableWebSecurity
// https://blog.csdn.net/yccccc_c/article/details/105877793#:~:text=%E5%8A%A8%E6%80%81%E6%9D%83%E9%99%90%E8%AE%BE%E7%BD%AE%E6%98%AF%E5%9F%BA,%E5%BE%84%E6%97%A0%E9%9C%80%E7%94%A8%E6%88%B7%E8%A7%92%E8%89%B2%EF%BC%9B
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(type = GlobalSecurityFilter.class)
    private GlobalSecurityFilter globalSecurityFilter;

    @Resource(type = GlobalAccessDecisionManager.class)
    private GlobalAccessDecisionManager globalAccessDecisionManager;

    @Resource(type = JwtFilter.class)
    private JwtFilter jwtFilter;

    @Resource(type = AppConfiguration.class)
    private AppConfiguration appConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> antMatchers = appConfiguration.getSecurity().getAntMatchers();
        String antMatchersStr = Joiner.on(Globals.COMMA).join(antMatchers);
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(globalAccessDecisionManager);
                        o.setSecurityMetadataSource(globalSecurityFilter);
                        return o;
                    }
                })
                .antMatchers(antMatchersStr).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    /**
     * 跨域配置
     *
     * @return 跨域配置
     */
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔;
        AppConfiguration.Security security = appConfiguration.getSecurity();
        security.getAllowedOrigin().forEach(corsConfiguration::addAllowedOrigin);
        // header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        security.getAllowedHeader().forEach(corsConfiguration::addAllowedHeader);
        // 允许的请求方法，PSOT、GET等
        security.getAllowedHeader().forEach(corsConfiguration::addAllowedMethod);
        // 配置允许跨域访问的url
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
