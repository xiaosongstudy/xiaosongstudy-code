package com.gitee.xiaosongstudy.request.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author hopeurl
 */
@Data
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 5946055351682469927L;

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 出生日期
     */
    private LocalDate birthday;
}
