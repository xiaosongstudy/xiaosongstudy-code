package com.gitee.xiaosongstudy.fcs.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 文件项
 *
 * @author hopeurl
 * @email shiping.song
 * @since 2022/10/12 14:58
 */
@Getter
@Setter
public class FileItem implements Serializable {
    private static final long serialVersionUID = -3849467385889703243L;

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 分片编号
     */
    private Integer chunkNumber;

    /**
     * 文件数据
     */
    private byte[] data;

    /**
     * 当前上传文件
     */
    private File currentFile;

    /**
     * 当前分片大小
     */
    private BigDecimal currentChunkSize;

    /**
     * 文件总大小
     */
    private BigDecimal totalSize;

    /**
     * 总分片数
     */
    private Integer totalChunks;
}
