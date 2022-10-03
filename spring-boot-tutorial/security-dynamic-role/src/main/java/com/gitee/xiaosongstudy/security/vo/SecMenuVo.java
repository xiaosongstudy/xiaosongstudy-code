package com.gitee.xiaosongstudy.security.vo;

import com.gitee.xiaosongstudy.security.entity.SecMenu;
import com.gitee.xiaosongstudy.security.entity.SecRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author hopeurl
 */
@ToString
@Setter
@Getter
public class SecMenuVo extends SecMenu {

    private static final long serialVersionUID = -4730525581372381404L;
    /**
     * 角色信息
     */
    private List<SecRole> secRoleList;
}
