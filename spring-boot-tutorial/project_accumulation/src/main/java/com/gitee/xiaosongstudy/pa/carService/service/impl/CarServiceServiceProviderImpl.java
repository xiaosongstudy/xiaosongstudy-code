package com.gitee.xiaosongstudy.pa.carService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.pa.carService.entity.CarService;
import com.gitee.xiaosongstudy.pa.carService.entity.CarServiceProvider;
import com.gitee.xiaosongstudy.pa.carService.mapper.CarServiceProviderMapper;
import com.gitee.xiaosongstudy.pa.carService.model.ServiceCascadeModel;
import com.gitee.xiaosongstudy.pa.carService.service.CarServiceProviderService;
import com.gitee.xiaosongstudy.pa.carService.service.CarServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hopeurl
 * @description 针对表【car_service(车辆服务记录表)】的数据库操作Service实现
 * @createDate 2023-03-15 01:07:29
 */
@Service
public class CarServiceServiceProviderImpl extends ServiceImpl<CarServiceProviderMapper, CarServiceProvider>
        implements CarServiceProviderService {

    @Resource
    private CarServiceService carServiceService;

    @Override
    public List<ServiceCascadeModel> listService() {
        List<ServiceCascadeModel> carServiceProviderList = new ArrayList<>();
        List<CarServiceProvider> spList = this.list();
        List<CarService> allServiceList = carServiceService.listServiceByCondtion();
        Map<String, List<CarService>> spRefcodeToCaServiceList = new HashMap<>();
        allServiceList.forEach(localService -> {
            String localKey = localService.getSpRefcode() + localService.getServiceClassId();
            CarService newCarService = new CarService();
            BeanUtils.copyProperties(localService, newCarService);
            if (spRefcodeToCaServiceList.containsKey(localKey)) {
                spRefcodeToCaServiceList.get(localKey).add(newCarService);
            } else {
                List<CarService> tempList = new ArrayList<>();
                tempList.add(newCarService);
                spRefcodeToCaServiceList.put(localKey, tempList);
            }
        });
        // 对所有服务信息进行预先的排序，根据spRefcode、serviceClassId以及serviceId
        spList.forEach(localSp -> {
            ServiceCascadeModel localServiceCascadeModel = new ServiceCascadeModel();
            localServiceCascadeModel.setSpShortName(localSp.getSpShortName());
            localServiceCascadeModel.setRefcode(localSp.getRefcode());
            List<CarService> serviceList = new ArrayList<>();
            Set<String> tempKey = new HashSet<>();
            allServiceList.forEach(localService -> {
                String localTempKey = localService.getSpRefcode() + localService.getServiceClassId();
                if (localService.getSpRefcode().equals(localSp.getRefcode()) && !tempKey.contains(localTempKey)) {
                    // 设置服务类型
                    List<CarService> localServiceTypeList = spRefcodeToCaServiceList.get(localService.getSpRefcode() + localService.getServiceClassId());
                    if (!CollectionUtils.isEmpty(localServiceTypeList)) {
                        localService.setServiceTypeList(CollectionUtils.isEmpty(localServiceTypeList) ? null : localServiceTypeList);
                    }
                    CarService carService = new CarService();
                    BeanUtils.copyProperties(localService, carService);
                    serviceList.add(carService);
                } else {
                    tempKey.add(localTempKey);
                }
            });
            localServiceCascadeModel.setServiceList(serviceList);
            carServiceProviderList.add(localServiceCascadeModel);
        });
        return carServiceProviderList;
    }
}




