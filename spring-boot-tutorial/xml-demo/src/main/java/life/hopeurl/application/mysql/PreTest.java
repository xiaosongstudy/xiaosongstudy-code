package life.hopeurl.application.mysql;

import life.hopeurl.application.mysql.example.entity.XmlUser;
import life.hopeurl.application.mysql.example.mapper.UserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 前置测试
 *
 * @author shiping.song
 * @date 2023/5/2 15:12
 */
public class PreTest {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.COMBO_POOLED_DATA_SOURCE);

    /**
     * 查询
     *
     * @date 2023/5/2 15:12
     */
    @Test
    public void query() {
        String sql = "select * from xml_user limit 100";
        List<XmlUser> xmlUserList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(XmlUser.class));
        Assert.assertTrue(CollectionUtils.isNotEmpty(xmlUserList));
        xmlUserList.forEach(System.out::println);
    }

    /**
     * 改造后的第一版测试
     * @date 2023/5/2 17:07
     */
    @Test
    public void query02() {
        UserMapper userMapper = MapperFactory.getInstance(UserMapper.class);
        List<XmlUser> xmlUserList = userMapper.listUserByPage();
        if (CollectionUtils.isNotEmpty(xmlUserList)) {
            xmlUserList.forEach(System.out::println);
        }
    }
}
