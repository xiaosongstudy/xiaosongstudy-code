package life.hopeurl.application.mysql.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * xml模块用户
 * @author songshiping
 * @TableName xml_user
 */
@Data
public class XmlUser implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 总资产
     */
    private BigDecimal totalAsset;

    /**
     * 个人介绍
     */
    private String introduction;

    private static final long serialVersionUID = 1L;
}