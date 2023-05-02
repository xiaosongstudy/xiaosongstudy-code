package life.hopeurl.application.mysql;

import lombok.Data;

import java.util.List;

/**
 * sql命令行
 * @author shiping.song
 * @date 2023/5/2 17:32
 */
@Data
public class SqlCommand {

    /**
     * 编号，对于同一个mapper接口而言具有唯一性
     */
    private String id;

    /**
     * 待执行的sql
     */
    private String sql;

    /**
     * 结果集
     */
    private String resultType;

    /**
     * 参数名称
     */
    private List<String> paramNames;

    /**
     * 当前执行的sql类型
     */
    private CommandType commandType;

    /**
     * 当前执行的sql的类型
     */
    enum CommandType {
        INSERT,DELETE,UPDATE,SELECT
    }

}
