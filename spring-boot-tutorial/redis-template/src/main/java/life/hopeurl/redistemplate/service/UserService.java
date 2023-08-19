package life.hopeurl.redistemplate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import life.hopeurl.redistemplate.entity.UserInfo;
import life.hopeurl.redistemplate.vo.UserInfoVo;

/**
 * 用户服务类接口
 *
 * @author shiping.song
 * @date 2023/8/18 22:47
 * @email 2453332538@qq.com
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 向指定手机号发送短信验证码
     *
     * @param mobilePhone 手机号
     * @return 验证码
     * @date 2023/8/18 23:22
     */
    String sendSmsCheckCode(String mobilePhone);

    /**
     * 用户登录
     *
     * @param userInfoVo 登录信息
     * @return 脱敏的用户信息
     * @date 2023/8/18 23:30
     */
    UserInfoVo login(UserInfoVo userInfoVo);
}
