package com.gitee.xiaosongstudy.hopeurlfilecenter.service;

import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;
import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileLocalStorage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hopeurl
* @description 针对表【file_local_storage(文件存储)】的数据库操作Service
* @createDate 2022-10-12 13:08:34
*/
public interface FileLocalStorageService extends IService<FileLocalStorage> {

    /**
     * 保存本地存储记录
     * @param param
     */
    void saveLocalStorage(FileChunk param);
}
