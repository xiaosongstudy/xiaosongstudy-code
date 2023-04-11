package com.gitee.xiaosongstudy.pa.carService.model;

import com.gitee.xiaosongstudy.pa.carService.entity.CarService;
import com.gitee.xiaosongstudy.pa.carService.entity.CarServiceProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 服务级联选择辅助数据模型
 * @author shiping.song
 * @date 2023/3/15 1:25
 * @since 1.0.0
 */
@ToString
@Getter
@Setter
public class ServiceCascadeModel extends CarServiceProvider {
    private static final long serialVersionUID = 4020544937219986009L;

    /**
     * 服务类型列表
     */
    private List<CarService> serviceList;
}
