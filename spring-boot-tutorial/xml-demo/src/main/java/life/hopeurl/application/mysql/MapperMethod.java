package life.hopeurl.application.mysql;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Method;

/**
 * mapper 方法
 *
 * @author shiping.song
 * @date 2023/5/2 16:59
 */
public class MapperMethod {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.COMBO_POOLED_DATA_SOURCE);

    /**
     * 执行方法
     *
     * @param bean   执行对象
     * @param method 待执行方法
     * @param args   方法入参
     * @return 响应参数
     * @date 2023/5/2 17:02
     */
    @SneakyThrows
    public Object invoke(Class<?> mapperClz, Method method, Object[] args) {
        SqlCommand sqlCommand = XmlCache.getCommandById(mapperClz, method.getName());
        String resultType = sqlCommand.getResultType();
        if (StringUtils.isNoneBlank(resultType)) {
            if (SqlCommand.CommandType.SELECT == sqlCommand.getCommandType()) {
                Class<?> resultClz = Class.forName(sqlCommand.getResultType());
                return jdbcTemplate.query(sqlCommand.getSql(), new BeanPropertyRowMapper<>(resultClz));
            }
        } else {
            jdbcTemplate.execute(sqlCommand.getSql());
        }
        return null;
    }
}
