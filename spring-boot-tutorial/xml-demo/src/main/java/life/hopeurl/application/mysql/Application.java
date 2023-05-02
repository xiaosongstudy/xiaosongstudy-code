package life.hopeurl.application.mysql;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用配置类
 * @author shiping.song
 * @date 2023/5/2 13:30
 */
@Data
public class Application implements Serializable {
    private static final long serialVersionUID = -5276991623393637172L;

    /**
     * mysql相关配置
     */
    private DataSource mysql;
}
