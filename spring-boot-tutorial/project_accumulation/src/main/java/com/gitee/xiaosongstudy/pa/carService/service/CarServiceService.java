package com.gitee.xiaosongstudy.pa.carService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.xiaosongstudy.pa.carService.entity.CarService;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【car_service(车辆服务记录表)】的数据库操作Service
* @createDate 2023-03-15 01:07:29
*/
public interface CarServiceService extends IService<CarService> {

    List<CarService> listServiceByCondtion();
}
