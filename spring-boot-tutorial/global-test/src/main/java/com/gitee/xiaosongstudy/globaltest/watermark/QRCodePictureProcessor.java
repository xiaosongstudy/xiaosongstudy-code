package com.gitee.xiaosongstudy.globaltest.watermark;

import org.springframework.stereotype.Component;

/**
 * 二维码水印
 */
@Component
public class QRCodePictureProcessor extends AbstractPictureProcessor {
    /**
     * 图片处理器本地线程参数
     */
    private final ThreadLocal<String> pictureProcessParamThreadLocal = new ThreadLocal<>();

    /**
     * 设置图片处理参数
     * @param params 待设置参数
     * @author shiping.song
     * @date 2023/4/20 20:14
     */
    public void setPictureProcessParam(String params) {
        if (null == pictureProcessParamThreadLocal.get()) {
            pictureProcessParamThreadLocal.set(params);
        }
    }

    /**
     * 清除图片处理器参数
     *
     * @author shiping.song
     * @date 2022/12/1 20:45
     */
    public void remove() {
        pictureProcessParamThreadLocal.remove();
    }

}