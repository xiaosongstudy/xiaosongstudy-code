package com.gitee.xiaosongstudy.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口表
 * @TableName sec_interface
 */
@TableName(value ="sec_interface")
@Data
public class SecInterface implements Serializable {
    /**
     * 接口编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 模块路径
     */
    private String modulePath;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 版本信息
     */
    private String version;

    /**
     * 权限type[1=同时满足(默认)/0=满足其中一个即可]'
     */
    private String permType;

    /**
     * 是否可用[0=不可用/1=可用(默认)]
     */
    private String useFlag;

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
        SecInterface other = (SecInterface) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getModulePath() == null ? other.getModulePath() == null : this.getModulePath().equals(other.getModulePath()))
            && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUseFlag() == null ? other.getUseFlag() == null : this.getUseFlag().equals(other.getUseFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModulePath() == null) ? 0 : getModulePath().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUseFlag() == null) ? 0 : getUseFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modulePath=").append(modulePath);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", path=").append(path);
        sb.append(", description=").append(description);
        sb.append(", version=").append(version);
        sb.append(", useFlag=").append(useFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}