package com.gitee.xiaosongstudy.fcs.utils;

import com.gitee.xiaosongstudy.fcs.config.FileCenterConfig;
import com.gitee.xiaosongstudy.fcs.model.Result;

/**
 * 文件工具类 .<br>
 *
 * @author shiping.song
 * @date 2022/10/15 16:21
 */
public final class FileUtil {

    private final FileCenterConfig fileCenterConfig;

    public FileUtil(FileCenterConfig fileCenterConfig) {
        this.fileCenterConfig = fileCenterConfig;
    }

    /**
     * TODO 上传文件
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 16:28
     */
    public Result uploadFile() {
        return null;
    }

    /**
     * TODO 下载文件
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 16:29
     */
    public Result downLoadFile() {
        return null;
    }

    public FileCenterConfig getFileCenterConfig() {
        return fileCenterConfig;
    }
}
