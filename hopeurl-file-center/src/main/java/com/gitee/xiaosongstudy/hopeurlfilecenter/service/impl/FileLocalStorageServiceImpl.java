package com.gitee.xiaosongstudy.hopeurlfilecenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;
import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileLocalStorage;
import com.gitee.xiaosongstudy.hopeurlfilecenter.mapper.FileLocalStorageMapper;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileLocalStorageService;
import com.gitee.xiaosongstudy.hopeurlfilecenter.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
* @author hopeurl
* @description 针对表【file_local_storage(文件存储)】的数据库操作Service实现
* @createDate 2022-10-12 13:08:34
*/
@Service
public class FileLocalStorageServiceImpl extends ServiceImpl<FileLocalStorageMapper, FileLocalStorage>
    implements FileLocalStorageService{
    @Override
    public void saveLocalStorage(FileChunk param) {
        LambdaQueryWrapper<FileLocalStorage> conditionWrappers = Wrappers.lambdaQuery(FileLocalStorage.class).eq(FileLocalStorage::getIdentifier, param.getIdentifier());
        Long id = null;
        FileLocalStorage byIdentifier = super.getOne(conditionWrappers);
        if (!ObjectUtils.isEmpty(byIdentifier)) {
            id = byIdentifier.getId();
        }
        String name = param.getFilename();
        String suffix = FileUtil.getExtensionName(name);
        String type = FileUtil.getFileType(suffix);
        FileLocalStorage localStorage = new FileLocalStorage(
                id,
                name,
                name,
                suffix,
                param.getRelativePath(),
                param.getParentPath(),
                type,
                FileUtil.getSize(param.getTotalSize()),
                param.getIdentifier()
        );
        this.save(localStorage);
    }
}




