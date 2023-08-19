package life.hopeurl.redistemplate.interceptor;

import life.hopeurl.redistemplate.core.Stores;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 权限拦截器
 *
 * @author shiping.song
 * @date 2023/8/18 22:50
 * @email 2453332538@qq.com
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Objects.isNull(Stores.getUserInfo())) {
            // 当前访问的接口是需要授权的接口
            // TODO 通过角色及接口具体权限进行控制
            // 当前处理方式比较简单，只要没有这里拿不到用户数据则直接拦截
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }
}
