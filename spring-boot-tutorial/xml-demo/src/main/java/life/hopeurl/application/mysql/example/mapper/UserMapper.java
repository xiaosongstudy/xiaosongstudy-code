package life.hopeurl.application.mysql.example.mapper;

import life.hopeurl.application.mysql.example.entity.XmlUser;

import java.util.List;

/**
 * xml用户模块持久化
 * @author shiping.song
 * @date 2023/5/2 15:34
 */
public interface UserMapper {

    /**
     * 分页获取所有用户信息
     * @return
     * @date 2023/5/2 16:28
     */
    List<XmlUser> listUserByPage();
}
