package com.gitee.xiaosongstudy.hopeurlfilecenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileChunkService;
import com.gitee.xiaosongstudy.hopeurlfilecenter.mapper.FileChunkMapper;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileLocalStorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author hopeurl
* @description 针对表【file_chunk】的数据库操作Service实现
* @createDate 2022-10-12 13:05:29
*/
@Service
public class FileChunkServiceImpl extends ServiceImpl<FileChunkMapper, FileChunk>
    implements FileChunkService{

    @Resource(type = FileChunkMapper.class)
    private FileChunkMapper fileChunkMapper;

    @Resource(type = FileLocalStorageService.class)
    private FileLocalStorageService fileLocalStorageService;

    @Override
    public List<FileChunk> findByMd5(String identifier) {
        LambdaQueryWrapper<FileChunk> conditionWrappers = Wrappers.lambdaQuery(FileChunk.class).eq(FileChunk::getIdentifier, identifier);
        return fileChunkMapper.selectList(conditionWrappers);
    }

    @Override
    public void saveFileChunk(FileChunk param) {
        param.setCreateTime(LocalDateTime.now());
        param.setUpdateTime(param.getCreateTime());
        fileChunkMapper.insert(param);
        // 当文件分片完整上传完成，存一份在LocalStorage表中
        if (param.getChunkNumber().equals(param.getTotalChunks())) {
            fileLocalStorageService.saveLocalStorage(param);
        }
    }
}




