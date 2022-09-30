package com.gitee.xiaosongstudy.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单项
 * @TableName sec_menu
 */
@TableName(value ="sec_menu")
@Data
public class SecMenu implements Serializable {
    /**
     * 菜单编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 模块路径
     */
    private String modulePath;

    /**
     * 路由地址
     */
    private String path;

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
        SecMenu other = (SecMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getModulePath() == null ? other.getModulePath() == null : this.getModulePath().equals(other.getModulePath()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModulePath() == null) ? 0 : getModulePath().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
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
        sb.append(", path=").append(path);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}