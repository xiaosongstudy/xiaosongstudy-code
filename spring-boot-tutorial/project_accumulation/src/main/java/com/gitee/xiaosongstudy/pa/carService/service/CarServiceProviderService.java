package com.gitee.xiaosongstudy.pa.carService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.xiaosongstudy.pa.carService.entity.CarServiceProvider;
import com.gitee.xiaosongstudy.pa.carService.model.ServiceCascadeModel;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【car_service(车辆服务记录表)】的数据库操作Service
* @createDate 2023-03-15 01:07:29
*/
public interface CarServiceProviderService extends IService<CarServiceProvider> {

    /**
     *  获取服务列表
     * @return
     * @author shiping.song
     * @date 2023/3/15 1:44
     */
    List<ServiceCascadeModel> listService();
}
