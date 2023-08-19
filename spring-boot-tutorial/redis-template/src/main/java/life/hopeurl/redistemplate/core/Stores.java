package life.hopeurl.redistemplate.core;

import life.hopeurl.redistemplate.vo.UserInfoVo;

/**
 * 全局仓库类
 *
 * @author shiping.song
 * @date 2023/8/18 22:19
 * @email 2453332538@qq.com
 */
public final class Stores {

    private Stores() {
    }

    private static final ThreadLocal<UserInfoVo> userInfo = new ThreadLocal<>();

    /**
     * 设置用户信息
     *
     * @param userInfo
     * @date 2023/8/18 22:26
     */
    public static void setUserInfo(UserInfoVo userInfo) {
        assert null != userInfo;
        // 清空敏感信息
        userInfo.setPassword(null);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     * @date 2023/8/18 22:27
     */
    public static UserInfoVo getUserInfo() {
        return userInfo.get();
    }

    /**
     * 销毁资源
     *
     * @date 2023/8/18 22:25
     */
    public static void destroy() {
        userInfo.remove();
    }
}
