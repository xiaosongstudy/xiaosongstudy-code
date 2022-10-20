package com.gitee.xiaosongstudy.sourcetrack.chapter001;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * beanA.<br>
 *
 * @author shiping.song
 * @date 2022/10/20 10:29
 */
@Component
@Slf4j
public class ComponentA {

    public ComponentA() {
        log.info("componentA初始化成功....");
    }
}
