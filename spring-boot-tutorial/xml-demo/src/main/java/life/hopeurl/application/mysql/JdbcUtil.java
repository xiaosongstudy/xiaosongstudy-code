package life.hopeurl.application.mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.Objects;

/**
 * jdbc工具类
 *
 * @author shiping.song
 * @date 2023/5/2 01:50
 */
public class JdbcUtil {

    /**
     * mysql连接本地线程变量
     */
    private static final ThreadLocal<Connection> CONNECTION_STORE = new ThreadLocal<>();

    /**
     * 数据库连接池
     */
    public static final ComboPooledDataSource COMBO_POOLED_DATA_SOURCE = new ComboPooledDataSource();

    /**
     * 数据源对象
     */
    private static final DataSource DATA_SOURCE;

    static {
        DATA_SOURCE = ResourceLoader.resolveConfiguration().getApplication().getMysql();
        COMBO_POOLED_DATA_SOURCE.setJdbcUrl(DATA_SOURCE.getUrl());
        COMBO_POOLED_DATA_SOURCE.setUser(DATA_SOURCE.getUsername());
        COMBO_POOLED_DATA_SOURCE.setPassword(DATA_SOURCE.getPassword());
    }

    private JdbcUtil() {
    }

    /**
     * 开启mysql连接
     *
     * @return 返回mysql连接
     * @date 2023/5/2 02:06
     */
    public static Connection openConnection() {
        if (Objects.isNull(CONNECTION_STORE.get())) {
            synchronized (JdbcUtil.class) {
                if (Objects.isNull(CONNECTION_STORE.get())) {
                    try {
                        return COMBO_POOLED_DATA_SOURCE.getConnection();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return CONNECTION_STORE.get();
    }

    /**
     * 释放资源
     *
     * @date 2023/5/2 13:55
     */
    public static void release() {
        CONNECTION_STORE.remove();
    }

    /**
     * 关闭资源
     *
     * @param connection 连接
     * @param statement  声明
     * @param resultSet  结果集
     * @date 2023/5/2 13:57
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (Objects.nonNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }
}
