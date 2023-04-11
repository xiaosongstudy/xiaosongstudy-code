package com.gitee.xiaosongstudy.pa.carService.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务提供商表
 * @author shiping.song
 * @date 2023/3/15 1:11
 * @since 1.0.0
 */
@Data
@TableName("car_service_provider")
public class CarServiceProvider implements Serializable {
    private static final long serialVersionUID = 7168481701270063220L;

    @TableId(type = IdType.AUTO)
    private Long refcode;

    @TableField
    private String spShortName;
}
