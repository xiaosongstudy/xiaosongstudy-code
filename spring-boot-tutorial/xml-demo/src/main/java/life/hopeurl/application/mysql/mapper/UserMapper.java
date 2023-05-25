package life.hopeurl.application.mysql.mapper;

import life.hopeurl.application.mysql.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author songshiping
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2023-05-20 09:53:41
 * @Entity life.hopeurl.application.mysql.entity.User
 */
public interface UserMapper {

    /**
     * 通过id编号和密码获取用户信息
     *
     * @param id 编号
     * @return 符合条件的用户列表
     * @date 2023/5/20 10:00
     */
    User getByIdAndPassword(@Param("refcode") Integer id, @Param("password") String password);

    /**
     * 通过条件获取用户信息
     *
     * @param condition 条件对象
     * @return 目标用户
     * @date 2023/5/20 15:03
     */
    User getByCondition(User condition);

    /**
     * 通过id编号获取用户信息
     *
     * @param id id编号
     * @return 指定用户
     * @date 2023/5/20 14:40
     */
    User getById(Integer id);

    /**
     * 新增用户并通过mysql自带的语句获取递增的主键
     * @param user
     * @date 2023/5/21 23:39
     */
    void insertUserAndReturnIdByMysql(User user);

    void insertUserAndReturnIdByMybatis(User user);

}




