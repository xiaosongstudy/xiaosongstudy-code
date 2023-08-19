package life.hopeurl.redistemplate.interceptor;

import cn.hutool.core.bean.BeanUtil;
import life.hopeurl.redistemplate.core.Stores;
import life.hopeurl.redistemplate.vo.UserInfoVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static life.hopeurl.redistemplate.constant.RedisConstant.LOGIN_USER_INFO_EXPIRE_KEY;
import static life.hopeurl.redistemplate.constant.RedisConstant.LOGIN_USER_INFO_KEY;

/**
 * 刷新token拦截器
 *
 * @author shiping.song
 * @date 2023/8/18 22:14
 * @email 2453332538@qq.com
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验用户信息是否存在，如果存在则刷新token，如果不存在则交由后续拦截器进行处理
        // 这里获取请求头中的信息
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(accessToken)) {
            String tokenKey = LOGIN_USER_INFO_KEY + accessToken;
            // 如果有用户凭证则从缓存中获取用户信息
            Map<Object, Object> userInfoMap = stringRedisTemplate.opsForHash().entries(tokenKey);
            if (!userInfoMap.isEmpty()) {
                // 如果不为空则自动续费token时间：默认为30分钟
                stringRedisTemplate.expire(tokenKey, LOGIN_USER_INFO_EXPIRE_KEY, TimeUnit.MINUTES);
                // 然后将用户信息存储到本地线程变量中
                UserInfoVo userInfoVo = BeanUtil.fillBeanWithMap(userInfoMap, new UserInfoVo(), false);
                Stores.setUserInfo(userInfoVo);
            }
        }
        // 无论有没有用户信息都放过请求
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束，释放资源
        Stores.destroy();
    }
}
