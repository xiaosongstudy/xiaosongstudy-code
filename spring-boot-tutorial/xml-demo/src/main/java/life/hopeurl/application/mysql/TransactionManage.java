package life.hopeurl.application.mysql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事物管理器
 * @author shiping.song
 * @date 2023/5/2 17:11
 */
public class TransactionManage {

    /**
     * 开启事务
     * @param connection
     * @date 2023/5/2 17:17
     */
    public static void beginTransaction(Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     * @param connection
     * @date 2023/5/2 17:18
     */
    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     * @param connection 当前连接
     * @date 2023/5/2 17:18
     */
    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
