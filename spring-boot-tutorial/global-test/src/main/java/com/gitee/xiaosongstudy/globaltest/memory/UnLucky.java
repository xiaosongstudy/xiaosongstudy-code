package com.gitee.xiaosongstudy.globaltest.memory;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 不幸的被连累者.<br>
 *
 * @author shiping.song
 * @date 2023/6/8 13:44
 */
@Service
public class UnLucky {

    /**
     * 随机数生成
     */
    private final Random seed = new Random();

    public List<String> extract(Integer size) {
        List<String> innerStrs = new ArrayList<>();
        IntStream.range(0, size).forEach(i -> innerStrs.add(seed.nextLong() + "" + seed.nextLong()));
        return innerStrs;
    }
}
