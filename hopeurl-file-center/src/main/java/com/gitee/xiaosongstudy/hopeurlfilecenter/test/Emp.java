package com.gitee.xiaosongstudy.hopeurlfilecenter.test;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 辅助测试员工类
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 9:46
 */
@Data
@Builder
public class Emp implements Serializable {
    private static final long serialVersionUID = -2214645541753741096L;

    private Integer empId;
    private String idNo;
}
