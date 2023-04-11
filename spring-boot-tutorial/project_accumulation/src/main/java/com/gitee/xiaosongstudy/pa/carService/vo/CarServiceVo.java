package com.gitee.xiaosongstudy.pa.carService.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

/**
 * 服务vo类
 * @author shiping.song
 * @date 2023/3/15 1:28
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class CarServiceVo implements Serializable {
    private static final long serialVersionUID = 2194076079125488270L;

    /**
     * 服务类型列表
     */
    private Collection<CarServiceVo> serviceTypeList;
}
