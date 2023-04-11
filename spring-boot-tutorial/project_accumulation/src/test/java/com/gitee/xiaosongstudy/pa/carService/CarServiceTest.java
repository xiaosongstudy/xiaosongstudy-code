package com.gitee.xiaosongstudy.pa.carService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.xiaosongstudy.pa.carService.service.CarServiceProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * carService测试
 *
 * @author shiping.song
 * @date 2023/3/15 3:08
 * @since 1.0.0
 */
@SpringBootTest
public class CarServiceTest {

    @Resource
    private CarServiceProviderService carServiceProviderService;

    @Test
    void testGetServiceList() {
        System.out.println(JSON.toJSONString(carServiceProviderService.listService(), SerializerFeature.DisableCircularReferenceDetect));
    }
}
