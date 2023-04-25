package com.gitee.xiaosongstudy.du.patterns.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局通用实体类
 * @author shiping.song
 * @date 2023/4/20 23:55
 * @since 1.0.0
 */
@Data
public class Common implements Serializable {

    private static final long serialVersionUID = 3052641743363347357L;

    /**
     * 测试编号
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
