package life.hopeurl.application.mysql;

import lombok.Data;

import java.io.Serializable;

/**
 * 核心配置类
 * @author shiping.song
 * @date 2023/5/2 13:30
 */
@Data
public class Configuration implements Serializable {
    private static final long serialVersionUID = -5276991623393637172L;

    /**
     * 应用配置类
     */
    private Application application;
}
