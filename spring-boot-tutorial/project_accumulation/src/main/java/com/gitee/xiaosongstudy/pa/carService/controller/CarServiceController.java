package com.gitee.xiaosongstudy.pa.carService.controller;

import com.gitee.xiaosongstudy.pa.carService.model.ServiceCascadeModel;
import com.gitee.xiaosongstudy.pa.carService.service.CarServiceProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车服控制器
 * @author shiping.song
 * @date 2023/3/15 2:34
 * @since 1.0.0
 */
@RestController
@RequestMapping("/carService")
public class CarServiceController {

    @Resource(type = CarServiceProviderService.class)
    private CarServiceProviderService carServiceProviderService;

    @GetMapping
    public List<ServiceCascadeModel> getServiceList() {
        return carServiceProviderService.listService();
    }
}
