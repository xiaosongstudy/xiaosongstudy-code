package com.gitee.xiaosongstudy.fcs.utils;

import com.gitee.xiaosongstudy.fcs.asserts.BusinessAssert;
import com.gitee.xiaosongstudy.fcs.config.FileCenterConfig;
import com.gitee.xiaosongstudy.fcs.model.FileItem;
import com.gitee.xiaosongstudy.fcs.model.Result;

import java.io.File;

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
     * @param fileItem 文件信息
     * @return
     * @author shiping.song
     * @date 2022/10/15 16:28
     */
    public Result singleUpload(FileItem fileItem) {
        return null;
    }

    /**
     * TODO 分片上传
     *
     * @return
     * @author shiping.song
     * @date 2022/10/19 17:38
     */
    public Result partialUpload(FileItem fileItem) {
        // 获取总文件大小
        File currentFile = fileItem.getCurrentFile();
        assertFileCenterConfig();
        // 1. 获取连接

        // 2. 分片
        // 3.
        return null;
    }

    /**
     *  校验文件中心代码
     * @author shiping.song
     * @date 2022/10/21 16:41
     */
    private void assertFileCenterConfig() {
        String serverAddress = fileCenterConfig.getServerAddress();
        BusinessAssert.hasText(serverAddress, "服务地址为空！");
        String serviceApiUrl = fileCenterConfig.getServiceApiUrl();
        BusinessAssert.hasText(serviceApiUrl,"请求资源为空！");

    }

    /**
     * TODO 加密单文件上传
     *
     * @param fileItem
     * @return
     * @author shiping.song
     * @date 2022/10/19 17:41
     */
    public Result securitySimpleUpload(FileItem fileItem) {
        return null;
    }

    /**
     * TODO 安全加密分片上传
     *
     * @param fileItem
     * @return
     * @author shiping.song
     * @date 2022/10/19 17:44
     */
    public Result securityPartialUpload(FileItem fileItem) {
        return null;
    }

    /**
     * TODO  单文件下载
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 16:29
     */
    public Result singleDownload() {
        return null;
    }

    public FileCenterConfig getFileCenterConfig() {
        return fileCenterConfig;
    }

    /**
     * 校验文件数据
     *
     * @param fileItem 待校验文件信息
     * @return
     * @author shiping.song
     * @date 2022/10/19 17:56
     */
    private boolean validateFileItem(FileItem fileItem) {
        return false;
    }
}
