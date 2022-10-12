package com.gitee.xiaosongstudy.hopeurlfilecenter.service;

import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;

import java.util.List;

/**
 * 文件处理服务类
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 15:32
 */
public interface FileService {
    /**
     * 上传文件
     * @param param 文件上传参数
     * @return 上传结果
     */
    boolean uploadFile(FileChunk param);

    /**
     * 通过文件唯一标识和查询类型匹配目标分片
     * @param identifier
     * @param type
     * @return
     */
    List<FileChunk> findByIdentifier(String identifier, String type);
}
