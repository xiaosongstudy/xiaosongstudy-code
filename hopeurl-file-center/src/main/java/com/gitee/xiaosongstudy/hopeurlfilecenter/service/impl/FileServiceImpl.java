package com.gitee.xiaosongstudy.hopeurlfilecenter.service.impl;

import com.gitee.xiaosongstudy.hopeurlfilecenter.config.AppConfig;
import com.gitee.xiaosongstudy.hopeurlfilecenter.constant.Globals;
import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileChunkService;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileLocalStorageService;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileService;
import com.gitee.xiaosongstudy.hopeurlfilecenter.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.List;

/**
 * 文件处理服务类实现类
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 15:32
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    /**
     * 默认分片大小为20M
     */
    public static final BigDecimal DEFAULT_CHUNK_SIZE = new BigDecimal(20).multiply(FileUtil.MB);

    @Resource
    private AppConfig appConfig;

    @Resource(type = FileChunkService.class)
    private FileChunkService fileChunkService;

    @Resource(type = FileLocalStorageService.class)
    private FileLocalStorageService fileLocalStorageService;

    @Override
    public boolean uploadFile(FileChunk param) {
        String savePath = appConfig.getFile().getSavePath();
        String fullFileName = savePath + File.separator + param.getFilename();
        // 单文件上传
        if (param.getTotalChunks() == 1) {
            return uploadSingleFile(fullFileName, param);
        }
        // 分片上传，这里使用 uploadFileByRandomAccessFile 方法，也可以使用 uploadFileByMappedByteBuffer 方法上传
        boolean flag = uploadFileByRandomAccessFile(fullFileName, param);
        if (!flag) {
            return false;
        }
        // 保存分片上传信息
        fileChunkService.saveFileChunk(param);
        return false;
    }

    /**
     * 分片上传文件
     *
     * @param fullFileName
     * @param param
     * @return
     */
    private boolean uploadFileByRandomAccessFile(String fullFileName, FileChunk param) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fullFileName, Globals.File.MODE_RW)) {
            // 分片大小必须和前端匹配，否则上传会导致文件损坏
            BigDecimal chunkSize = param.getChunkSize().compareTo(BigDecimal.ZERO) == 0 ? DEFAULT_CHUNK_SIZE : param.getChunkSize();
            // 偏移量
            BigDecimal offset = chunkSize.multiply(new BigDecimal(param.getChunkNumber() - 1));
            // 定位到该分片的偏移量
            randomAccessFile.seek(offset.longValue());
            // 写入
            randomAccessFile.write(param.getFile().getBytes());
        } catch (IOException e) {
            log.error("文件上传失败：" + e);
            return false;
        }
        return true;
    }

    /**
     * 上传单文件
     *
     * @param fullFileName 文件名称
     * @param param        分片参数
     * @return
     */
    private boolean uploadSingleFile(String fullFileName, FileChunk param) {
        File saveFile = new File(fullFileName);
        try {
            // 写入
            param.getFile().transferTo(saveFile);
            fileLocalStorageService.saveLocalStorage(param);
        } catch (IOException e) {
            log.error("文件上传失败：" + e);
            return false;
        }
        return true;
    }

    @Override
    public List<FileChunk> findByIdentifier(String identifier, String type) {
        return fileChunkService.findByMd5(identifier);
    }
}
