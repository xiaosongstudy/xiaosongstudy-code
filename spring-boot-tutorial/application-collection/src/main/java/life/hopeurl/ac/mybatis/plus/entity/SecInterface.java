package life.hopeurl.ac.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;

import java.io.Serializable;

/**
 * 接口表
 * @TableName sec_interface
 */
@TableName(value ="sec_interface")
@Builder
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
    @TableField(fill = FieldFill.INSERT)
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