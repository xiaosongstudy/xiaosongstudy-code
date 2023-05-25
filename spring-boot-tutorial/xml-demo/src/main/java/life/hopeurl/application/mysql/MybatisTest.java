package life.hopeurl.application.mysql;

import life.hopeurl.application.mysql.entity.User;
import life.hopeurl.application.mysql.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * mybatis测试
 *
 * @author shiping.song
 * @date 2023/5/20 09:55
 */
public class MybatisTest {

    private SqlSession sqlSession;

    private UserMapper userMapper;

    @Before
    public void pre() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testParam() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.getByIdAndPassword(1,"admin123"));
//        System.out.println(userMapper.getById(1));
//        System.out.println(userMapper.getByCondition(User.builder().id(1L).build()));
    }

    @Test
    public void testInsertUserAndReturnIdByMysql() {
        User user = User.builder().username("张三").password("admin123").build();
        userMapper.insertUserAndReturnIdByMysql(user);
        System.out.println(user);
    }

    @Test
    public void testInsertUserAndReturnIdByMybatis() {
        User user = User.builder().username("里斯").password("admin123").build();
        userMapper.insertUserAndReturnIdByMybatis(user);
        System.out.println(user);
    }


}
