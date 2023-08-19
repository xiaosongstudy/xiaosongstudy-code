package life.hopeurl.redistemplate.vo;

import life.hopeurl.redistemplate.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息视图
 *
 * @author shiping.song
 * @date 2023/8/18 22:22
 * @email 2453332538@qq.com
 */
@Getter
@Setter
@ToString
public class UserInfoVo extends UserInfo {
    private static final long serialVersionUID = -7036908422060961034L;

    /**
     * 用户toke凭证
     */
    private String accessToken;

    /**
     * 验证码
     */
    private String checkCode;

    /**
     * 这里图省事将错误信息字段直接加在测试vo中，实际这个字段会被加到公共处理响应类中
     */
    private String errorMsg;

    private UserInfoVo testChild;
}
