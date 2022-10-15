package com.gitee.xiaosongstudy.fcs.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 文件项
 * @author hopeurl
 * @email myhopeurlife@foxmail
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
}
