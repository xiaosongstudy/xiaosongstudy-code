package com.gitee.xiaosongstudy.globaltest.memory;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 内存泄漏.<br>
 *
 * @author shiping.song
 * @date 2023/6/8 13:43
 */
@Service
public class MemoryLeak {

    /**
     * 共享变量，内存泄漏主要原因
     */
    private List<String> sharedStrs;

    /**
     * 随机数生成
     */
    private final Random seed = new Random();

    private void init() {
        sharedStrs = new ArrayList<>();
    }

    /**
     * 方法执行完，没有清空共享变量，导致引用对象无法回收
     *
     * @param size
     * @return
     */
    public List<String> extract(Integer size) {
        // 方法开始时清空变量
        init();
        IntStream.range(0, size).forEach(i -> sharedStrs.add(seed.nextLong() + "" + seed.nextLong()));
        return sharedStrs;
    }

    /**
     * 清空
     *
     * @author shiping.song
     * @date 2023/6/8 14:13
     */
    public void clear() {
        sharedStrs = null;
    }
}
