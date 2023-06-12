package com.gitee.xiaosongstudy.globaltest.watermark;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 水印抽象基类
 */
public abstract class AbstractPictureProcessor {
    /**
     * 默认初始化5个处理器
     */
    private final ConcurrentHashMap<Class<?>, AbstractPictureProcessor> pictureProcessorConcurrentHashMap = new ConcurrentHashMap<>(5);

    public void addPictureProcessor(AbstractPictureProcessor pictureProcessor) {
        if (null != pictureProcessor && !pictureProcessorConcurrentHashMap.containsKey(pictureProcessor.getClass())) {
            pictureProcessorConcurrentHashMap.put(pictureProcessor.getClass(), pictureProcessor);
        }
    }

    /**
     * 解析完成后可以借由本方法进行后置处理
     *
     * @param abstractPictureProcessor 图像处理器
     * @author shiping.song
     * @date 2022/12/8 16:06
     */
    public void afterProcess(AbstractPictureProcessor abstractPictureProcessor) {

    }
}