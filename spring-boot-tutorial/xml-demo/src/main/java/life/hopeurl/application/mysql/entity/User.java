package life.hopeurl.application.mysql.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 * @author songshiping
 * @TableName user
 */
@Data
@Builder
public class User implements Serializable {
    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 
     */
    private String password;

    private static final long serialVersionUID = 1L;
}