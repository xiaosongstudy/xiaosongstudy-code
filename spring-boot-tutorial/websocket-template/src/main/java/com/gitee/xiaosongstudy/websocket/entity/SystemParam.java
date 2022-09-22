package com.gitee.xiaosongstudy.websocket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数
 * @TableName system_param
 */
@TableName(value ="system_param")
@Data
public class SystemParam implements Serializable {
    /**
     * 参数编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数描述
     */
    private String paramDesc;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数状态[0=在使用(默认)/1=未使用/2=已废弃]
     */
    private String paramStatus;

    /**
     * 从哪个版本开始
     */
    private String version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 最近更新时间
     */
    private Date lastUpdateTime;

    /**
     * 最近更新人
     */
    private String lastUpdateBy;

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
        SystemParam other = (SystemParam) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamName() == null ? other.getParamName() == null : this.getParamName().equals(other.getParamName()))
            && (this.getParamDesc() == null ? other.getParamDesc() == null : this.getParamDesc().equals(other.getParamDesc()))
            && (this.getParamValue() == null ? other.getParamValue() == null : this.getParamValue().equals(other.getParamValue()))
            && (this.getParamStatus() == null ? other.getParamStatus() == null : this.getParamStatus().equals(other.getParamStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateBy() == null ? other.getLastUpdateBy() == null : this.getLastUpdateBy().equals(other.getLastUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParamName() == null) ? 0 : getParamName().hashCode());
        result = prime * result + ((getParamDesc() == null) ? 0 : getParamDesc().hashCode());
        result = prime * result + ((getParamValue() == null) ? 0 : getParamValue().hashCode());
        result = prime * result + ((getParamStatus() == null) ? 0 : getParamStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getLastUpdateBy() == null) ? 0 : getLastUpdateBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", paramName=" + paramName +
                ", paramDesc=" + paramDesc +
                ", paramValue=" + paramValue +
                ", paramStatus=" + paramStatus +
                ", version=" + version +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", lastUpdateTime=" + lastUpdateTime +
                ", lastUpdateBy=" + lastUpdateBy +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}