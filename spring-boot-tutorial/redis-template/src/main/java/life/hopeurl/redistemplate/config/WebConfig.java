package life.hopeurl.redistemplate.config;

import life.hopeurl.redistemplate.interceptor.AuthInterceptor;
import life.hopeurl.redistemplate.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web应用配置
 *
 * @author shiping.song
 * @date 2023/8/18 22:12
 * @email 2453332538@qq.com
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token刷新是会拦截所有的请求，如果携带了token且缓存中有则会自动为用户续费token时间长度。
        // 如果用户携带了token但是缓存中没有，则直接放过，交由后续拦截器处理
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate));
        // 这个拦截器的作用是鉴权，这个拦截器会放过部分静态资源，然后会根据用户访问的资源进行权限校验
        registry.addInterceptor(new AuthInterceptor())
                .excludePathPatterns(
                        "/css/**", "/js/**",
                        // 发送验证码、登录接口不作控制
                        "/user/sendSmsCheckCode/*", "/user/login");
    }
}
