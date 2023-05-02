package life.hopeurl.application.mysql;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据源
 * @author shiping.song
 * @date 2023/5/2 02:38
 */
@Data
public class DataSource implements Serializable {
    private static final long serialVersionUID = -7882804907849274170L;
    private String username;
    private String password;
    private String url;
    private String driverClass;
}
