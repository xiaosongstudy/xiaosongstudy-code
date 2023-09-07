package life.hopeurl.redistemplate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author shiping.song
 * @date 2023/8/18 22:20
 * @email 2453332538@qq.com
 */
@Data
@TableName("user")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 5242164304308747265L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobilePhone;
}
