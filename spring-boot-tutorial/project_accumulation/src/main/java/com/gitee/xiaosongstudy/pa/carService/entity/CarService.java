package com.gitee.xiaosongstudy.pa.carService.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 车辆服务记录表
 *
 * @TableName car_service
 */
@TableName(value = "car_service")
@Data
public class CarService implements Serializable {
    /**
     * 流水号
     */
    @TableId(type = IdType.AUTO)
    private Long refcode;

    /**
     * 服务商流水号
     */
    private Long spRefcode;

    /**
     * 服务父级编号
     */
    private String serviceClassId;

    /**
     * 服务子编号
     */
    private String serviceId;

    /**
     * 父级服务名称
     */
    private String serviceClassName;

    /**
     * 具体服务名称
     */
    private String serviceName;

    /**
     * 服务描述
     */
    private String serviceDesc;

    /**
     * 服务类型列表
     */
    @TableField(exist = false)
    private List<CarService> serviceTypeList;
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
        CarService other = (CarService) that;
        return (this.getRefcode() == null ? other.getRefcode() == null : this.getRefcode().equals(other.getRefcode()))
                && (this.getSpRefcode() == null ? other.getSpRefcode() == null : this.getSpRefcode().equals(other.getSpRefcode()))
                && (this.getServiceClassId() == null ? other.getServiceClassId() == null : this.getServiceClassId().equals(other.getServiceClassId()))
                && (this.getServiceId() == null ? other.getServiceId() == null : this.getServiceId().equals(other.getServiceId()))
                && (this.getServiceClassName() == null ? other.getServiceClassName() == null : this.getServiceClassName().equals(other.getServiceClassName()))
                && (this.getServiceName() == null ? other.getServiceName() == null : this.getServiceName().equals(other.getServiceName()))
                && (this.getServiceDesc() == null ? other.getServiceDesc() == null : this.getServiceDesc().equals(other.getServiceDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRefcode() == null) ? 0 : getRefcode().hashCode());
        result = prime * result + ((getSpRefcode() == null) ? 0 : getSpRefcode().hashCode());
        result = prime * result + ((getServiceClassId() == null) ? 0 : getServiceClassId().hashCode());
        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());
        result = prime * result + ((getServiceClassName() == null) ? 0 : getServiceClassName().hashCode());
        result = prime * result + ((getServiceName() == null) ? 0 : getServiceName().hashCode());
        result = prime * result + ((getServiceDesc() == null) ? 0 : getServiceDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", refcode=").append(refcode);
        sb.append(", spRefcode=").append(spRefcode);
        sb.append(", serviceClassId=").append(serviceClassId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", serviceClassName=").append(serviceClassName);
        sb.append(", serviceName=").append(serviceName);
        sb.append(", serviceDesc=").append(serviceDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}