package life.hopeurl.redistemplate.api;

import cn.hutool.http.server.HttpServerRequest;
import life.hopeurl.redistemplate.service.UserService;
import life.hopeurl.redistemplate.vo.UserInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器 控制器
 *
 * @author shiping.song
 * @date 2022/12/25 22:38
 * @since 1.0.0
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 发送短信验证码
     *
     * @param mobilePhone 手机号码
     * @return 短信验证码
     * @date 2023/8/18 23:17
     */
    @GetMapping("/sendSmsCheckCode/{mobilePhone}")
    public String sendSmsCheckCode(@PathVariable String mobilePhone, HttpServerRequest httpRequest) {
        // 这里可以结合httpRequest及redis对发送短信进行频控，例如：多长时间内用户可以重新获取验证码，是否支持多地登录，是否支持多端登录，一个ip能获取几次验证码等等
        // 这里就简单处理，直接裸奔，直接发短信，当然实际是需要接入第三方短信服务商的，这里也省略
        return userService.sendSmsCheckCode(mobilePhone);
    }

    /**
     * 用户登录
     *
     * @param userInfoVo
     * @return 用户信息
     * @date 2023/8/18 23:28
     */
    @PostMapping("/login")
    public UserInfoVo login(@RequestBody UserInfoVo userInfoVo) {
        // 实际controller内会做许多的参数校验，包括必填值的非空校验、长度校验，非必填的长度校验等等，部分向邮箱手机号等还需要校额外校验格式，这里均省略
        return userService.login(userInfoVo);
    }
}
