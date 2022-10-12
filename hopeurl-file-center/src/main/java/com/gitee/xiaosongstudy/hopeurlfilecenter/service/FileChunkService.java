package com.gitee.xiaosongstudy.hopeurlfilecenter.service;

import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hopeurl
* @description 针对表【file_chunk】的数据库操作Service
* @createDate 2022-10-12 13:05:29
*/
public interface FileChunkService extends IService<FileChunk> {

    /**
     * 通过md5标识获取文件分片信息
     * @param identifier md5标识
     * @return
     */
    List<FileChunk> findByMd5(String identifier);

    /**
     * 保存分片信息
     * @param param
     */
    void saveFileChunk(FileChunk param);
}
