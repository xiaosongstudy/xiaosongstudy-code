package com.gitee.xiaosongstudy.security.config;

import com.gitee.xiaosongstudy.security.core.GlobalAccessDecisionManager;
import com.gitee.xiaosongstudy.security.filters.GlobalSecurityFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity // https://blog.csdn.net/yccccc_c/article/details/105877793#:~:text=%E5%8A%A8%E6%80%81%E6%9D%83%E9%99%90%E8%AE%BE%E7%BD%AE%E6%98%AF%E5%9F%BA,%E5%BE%84%E6%97%A0%E9%9C%80%E7%94%A8%E6%88%B7%E8%A7%92%E8%89%B2%EF%BC%9B
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userServiceImpl")
    private UserDetailsService userService;

    @Resource(type = GlobalSecurityFilter.class)
    private GlobalSecurityFilter globalSecurityFilter;

    @Resource(type = GlobalAccessDecisionManager.class)
    private GlobalAccessDecisionManager globalAccessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(globalAccessDecisionManager);
                        o.setSecurityMetadataSource(globalSecurityFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
