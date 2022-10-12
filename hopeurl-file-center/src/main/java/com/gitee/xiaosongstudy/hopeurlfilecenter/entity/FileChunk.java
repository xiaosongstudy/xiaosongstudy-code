package com.gitee.xiaosongstudy.hopeurlfilecenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @TableName file_chunk
 */
@TableName(value ="file_chunk")
@Data
public class FileChunk implements Serializable {
    /**
     * 分片记录编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件名
     */
    @TableField("file_name")
    private String filename;

    /**
     * 当前分片，从1开始
     */
    private Integer chunkNumber;

    /**
     * 分片大小
     */
    private BigDecimal chunkSize;

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
    @TableField("total_chunk")
    private Integer totalChunks;

    /**
     * 文件标识
     */
    private String identifier;

    /**
     * md5校验码
     */
    private String relativePath;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    @TableField(exist = false)
    private MultipartFile file;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FileChunk other = (FileChunk) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
            && (this.getChunkNumber() == null ? other.getChunkNumber() == null : this.getChunkNumber().equals(other.getChunkNumber()))
            && (this.getChunkSize() == null ? other.getChunkSize() == null : this.getChunkSize().equals(other.getChunkSize()))
            && (this.getCurrentChunkSize() == null ? other.getCurrentChunkSize() == null : this.getCurrentChunkSize().equals(other.getCurrentChunkSize()))
            && (this.getTotalSize() == null ? other.getTotalSize() == null : this.getTotalSize().equals(other.getTotalSize()))
            && (this.getTotalChunks() == null ? other.getTotalChunks() == null : this.getTotalChunks().equals(other.getTotalChunks()))
            && (this.getIdentifier() == null ? other.getIdentifier() == null : this.getIdentifier().equals(other.getIdentifier()))
            && (this.getRelativePath() == null ? other.getRelativePath() == null : this.getRelativePath().equals(other.getRelativePath()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getChunkNumber() == null) ? 0 : getChunkNumber().hashCode());
        result = prime * result + ((getChunkSize() == null) ? 0 : getChunkSize().hashCode());
        result = prime * result + ((getCurrentChunkSize() == null) ? 0 : getCurrentChunkSize().hashCode());
        result = prime * result + ((getTotalSize() == null) ? 0 : getTotalSize().hashCode());
        result = prime * result + ((getTotalChunks() == null) ? 0 : getTotalChunks().hashCode());
        result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
        result = prime * result + ((getRelativePath() == null) ? 0 : getRelativePath().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileName=").append(filename);
        sb.append(", chunkNumber=").append(chunkNumber);
        sb.append(", chunkSize=").append(chunkSize);
        sb.append(", currentChunkSize=").append(currentChunkSize);
        sb.append(", totalSize=").append(totalSize);
        sb.append(", totalChunk=").append(totalChunks);
        sb.append(", identifier=").append(identifier);
        sb.append(", relativePath=").append(relativePath);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}