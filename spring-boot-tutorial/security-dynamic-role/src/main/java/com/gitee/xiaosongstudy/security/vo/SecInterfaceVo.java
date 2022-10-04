package com.gitee.xiaosongstudy.security.vo;

import com.gitee.xiaosongstudy.security.entity.SecInterface;
import com.gitee.xiaosongstudy.security.entity.SecMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 接口视图类 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/3 19:00
 */
@Getter
@Setter
@ToString
public class SecInterfaceVo extends SecInterface {
    private static final long serialVersionUID = -4887591876826453421L;

    /**
     * 权限信息
     */
    private String perms;

    /**
     * 菜单是否可用
     */
    private String menuUseFlag;

    /**
     * 权限列表
     */
    private List<SecMenu> permList;
}
