package com.gitee.xiaosongstudy.globaltest.controller;

import com.gitee.xiaosongstudy.globaltest.memory.MemoryLeak;
import com.gitee.xiaosongstudy.globaltest.memory.UnLucky;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试controller.<br>
 *
 * @author shiping.song
 * @date 2023/6/8 13:50
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private MemoryLeak memoryLeak;

    @Resource
    private UnLucky unLucky;

    @GetMapping
    public void memoryTest() {
        memoryLeak.extract(60000);
        memoryLeak.clear();
        unLucky.extract(40000);
    }
}
