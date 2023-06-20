package life.hopeurl.ac.mybatis.plus.controller;

import life.hopeurl.ac.mybatis.plus.entity.SecInterface;
import life.hopeurl.ac.mybatis.plus.mapper.SecInterfaceMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * mybatis plus测试controller
 * @author shiping.song
 * @date 2023/6/20 22:05
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisPlusController {

    @Resource
    private SecInterfaceMapper secInterfaceMapper;

    /**
     * 测试handler处理器
     * @return 测试字符串
     * @date 2023/6/20 22:09
     */
    @PostMapping("/testHandler")
    public String testHandler() {
        SecInterface sec = SecInterface.builder()
                .description("我是描述")
                .build();
        secInterfaceMapper.insert(sec);
        return "testHandler";
    }
}
