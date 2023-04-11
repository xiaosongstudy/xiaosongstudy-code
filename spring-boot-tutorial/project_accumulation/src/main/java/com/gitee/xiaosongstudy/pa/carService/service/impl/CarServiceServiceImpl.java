package com.gitee.xiaosongstudy.pa.carService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.pa.carService.entity.CarService;
import com.gitee.xiaosongstudy.pa.carService.mapper.CarServiceMapper;
import com.gitee.xiaosongstudy.pa.carService.service.CarServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【car_service(车辆服务记录表)】的数据库操作Service实现
* @createDate 2023-03-15 01:07:29
*/
@Service
public class CarServiceServiceImpl extends ServiceImpl<CarServiceMapper, CarService>
    implements CarServiceService{

    @Override
    public List<CarService> listServiceByCondtion() {
        LambdaQueryWrapper<CarService> wr = Wrappers.lambdaQuery(CarService.class);
        wr.orderByDesc(CarService::getSpRefcode, CarService::getServiceClassId,CarService::getServiceId);
        return super.list(wr);
    }
}




