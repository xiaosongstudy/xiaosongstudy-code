package life.hopeurl.redistemplate.constant;

/**
 * redis常量类
 *
 * @author shiping.song
 * @date 2023/8/18 22:57
 * @email 2453332538@qq.com
 */
public final class RedisConstant {

    private RedisConstant() {

    }

    /**
     * 用户信息key
     */
    public static final String LOGIN_USER_INFO_KEY = "login:userInfo:";

    /**
     * 用户token过期时间
     */
    public static final Long LOGIN_USER_INFO_EXPIRE_KEY = 30L;

    /**
     * 短信验证码key
     */
    public static final String LOGIN_SMS_KEY = "login:checkCode:sms:";

    /**
     * 短信验证码过期时间
     */
    public static final Long LOGIN_SMS_EXPIRE_KEY = 3L;
}
