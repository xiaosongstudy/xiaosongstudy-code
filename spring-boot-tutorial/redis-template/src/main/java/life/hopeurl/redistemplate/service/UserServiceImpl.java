package life.hopeurl.redistemplate.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hopeurl.redistemplate.entity.UserInfo;
import life.hopeurl.redistemplate.mapper.UserMapper;
import life.hopeurl.redistemplate.vo.UserInfoVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static life.hopeurl.redistemplate.constant.RedisConstant.LOGIN_SMS_EXPIRE_KEY;
import static life.hopeurl.redistemplate.constant.RedisConstant.LOGIN_SMS_KEY;
import static life.hopeurl.redistemplate.constant.RedisConstant.LOGIN_USER_INFO_EXPIRE_KEY;
import static life.hopeurl.redistemplate.constant.RedisConstant.LOGIN_USER_INFO_KEY;

/**
 * 用户服务类接口
 *
 * @author shiping.song
 * @date 2023/8/18 22:48
 * @email 2453332538@qq.com
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String sendSmsCheckCode(String mobilePhone) {
        String checkCode = RandomUtil.randomNumbers(6);
        // 省略实际发短信的过程
        // 向缓存中存储验证码并设置过期时间
        stringRedisTemplate.opsForValue().set(LOGIN_SMS_KEY + mobilePhone, checkCode, LOGIN_SMS_EXPIRE_KEY, TimeUnit.MINUTES);
        return checkCode;
    }

    @Override
    public UserInfoVo login(UserInfoVo userInfoVo) {
        // 校验手机号验证码是否存在，不存在则让用户重新获取验证码
        String smsCheckCodeKey = LOGIN_SMS_KEY + userInfoVo.getMobilePhone();
        String smsCheckCode = stringRedisTemplate.opsForValue().get(smsCheckCodeKey);
        UserInfoVo respUserInfo = new UserInfoVo();
        if (!StringUtils.hasText(smsCheckCode)) {
            respUserInfo.setErrorMsg("验证码已过期，请重新获取");
        } else {
            // 如果存在则校验验证码是否正确
            if (smsCheckCode.equals(userInfoVo.getCheckCode())) {
                // 验证通过则删除验证码
                stringRedisTemplate.delete(smsCheckCodeKey);
                // 这里其实需要控制一下并发问题，暂时也不做考虑，之后再完善
                // 如果正确则校验用户是否存在
                LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery(UserInfo.class)
                        .eq(UserInfo::getMobilePhone, userInfoVo.getMobilePhone());
                UserInfo userInfo = super.getOne(queryWrapper);
                if (Objects.isNull(userInfo)) {
                    // 如果用户不存在则需要新增用户，并初始化默认密码  这里正常来说还需要要全局的事务控制，这里也不做处理
                    userInfo = new UserInfo();
                    userInfo.setMobilePhone(userInfoVo.getMobilePhone());
                    userInfo.setUsername(RandomUtil.randomNumbers(18));
                    // 这个是默认密码，后期用户可以自行修改
                    userInfo.setPassword(SecureUtil.md5(RandomUtil.randomNumbers(10)));
                    super.save(userInfo);
                }
                // 颁发token
                String accessToken = UUID.randomUUID().toString(true);
                respUserInfo.setAccessToken(accessToken);
                respUserInfo.setUsername(userInfo.getUsername());
                respUserInfo.setMobilePhone(userInfo.getMobilePhone());
                // 向缓存中设置缓存信息，同时设置过期时间
                Map<String, Object> userMap = BeanUtil.beanToMap(respUserInfo, new HashMap<>(), CopyOptions
                        .create().ignoreNullValue().setFieldValueEditor((fieldName, fieldValue) -> {
                            if (Objects.nonNull(fieldValue)) {
                                return fieldValue.toString();
                            }
                            return null;
                        }));
                String tokenKey = LOGIN_USER_INFO_KEY + accessToken;
                // 减少交互次数
                stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
                stringRedisTemplate.expire(tokenKey, LOGIN_USER_INFO_EXPIRE_KEY, TimeUnit.MINUTES);
            } else {
                // 真实情况下这里也需要控制这个验证码最多可以被校验多少次
                respUserInfo.setErrorMsg("验证码错误");
            }
        }
        return respUserInfo;
    }
}
