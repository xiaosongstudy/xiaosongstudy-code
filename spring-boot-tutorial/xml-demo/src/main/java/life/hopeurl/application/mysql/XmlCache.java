package life.hopeurl.application.mysql;

import life.hopeurl.application.mysql.execption.DuplicateMapperIdException;
import life.hopeurl.application.mysql.execption.UnbindException;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * xml缓存
 *
 * @author shiping.song
 * @date 2023/5/2 17:28
 */
public class XmlCache {

    /**
     * xml缓存，其中key为对应的mapper接口的字节码类型，value为sqlCommand集合
     */
    private static final ConcurrentHashMap<Class<?>, List<SqlCommand>> xmlStore = new ConcurrentHashMap<>();

    /**
     * 支持的sql语句类型
     */
    private static final String[] COMMAND_TYPES = {"insert", "delete", "update", "select"};

    @SneakyThrows
    public static void beginCache() {
        if (xmlStore.isEmpty()) {
            synchronized (XmlCache.class) {
                if (xmlStore.isEmpty()) {
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(XmlCache.class.getResourceAsStream("/mysql/XmlUserMapper.xml"));
                    Element mapperRootElement = document.getRootElement();
                    Attribute namespaceAttribute = mapperRootElement.attribute("namespace");
                    String namespace = namespaceAttribute.getText();
                    Class<?> mapperClz = Class.forName(namespace);
                    List<SqlCommand> sqlCommands = new ArrayList<>();
                    xmlStore.put(mapperClz, sqlCommands);
                    Set<String> idSet = new HashSet<>();
                    Iterator<Element> elementIterator = mapperRootElement.elementIterator();
                    while (elementIterator.hasNext()) {
                        Element element = elementIterator.next();
                        String name = element.getName();
                        if (StringUtils.isBlank(name)) {
                            continue;
                        }
                        name = name.toLowerCase();
                        if (ArrayUtils.contains(COMMAND_TYPES, name)) {
                            SqlCommand sqlCommand = new SqlCommand();
                            switch (name) {
                                case "select":
                                    sqlCommand.setCommandType(SqlCommand.CommandType.SELECT);
                                    break;
                                case "update":
                                    sqlCommand.setCommandType(SqlCommand.CommandType.UPDATE);
                                    break;
                                case "delete":
                                    sqlCommand.setCommandType(SqlCommand.CommandType.DELETE);
                                    break;
                                case "insert":
                                    sqlCommand.setCommandType(SqlCommand.CommandType.INSERT);
                                    break;
                                default:

                            }
                            setSqlCommand(idSet, element, sqlCommand);
                            sqlCommands.add(sqlCommand);
                        }
                    }
                }
            }
        }
    }

    /**
     * 设置sqlCommand公共部分
     *
     * @param idSet      编号set集合
     * @param element    当前节点元素
     * @param sqlCommand 当前解析的sql命令行对象
     * @date 2023/5/2 21:42
     */
    private static SqlCommand setSqlCommand(Set<String> idSet, Element element, SqlCommand sqlCommand) {
        Attribute id = element.attribute("id");
        Attribute resultType = element.attribute("resultType");
        if (Objects.nonNull(id)) {
            String idText = id.getText();
            if (idSet.contains(idText)) {
                throw new DuplicateMapperIdException(String.format("当前绑定的mapper id 编号%s已存在,请检查。", idText));
            } else {
                idSet.add(idText);
            }
            sqlCommand.setId(idText);
        }
        if (Objects.nonNull(resultType)) {
            sqlCommand.setResultType(resultType.getText());
        }
        sqlCommand.setSql(element.getText().trim());
        return sqlCommand;
    }


    /**
     * 获取指定mapper接口下的所有sql集合
     *
     * @param mapperClz 指定mapper接口class类型参数
     * @return 所有待执行的sql集合
     * @date 2023/5/2 21:41
     */
    public static List<SqlCommand> getSqlCommandList(Class<?> mapperClz) {
        return xmlStore.get(mapperClz);
    }

    /**
     * 通过id唯一编号获取指定mapper接口中待执行的sql信息
     *
     * @param mapperClz 指定mapper接口
     * @param id        唯一编号
     * @date 2023/5/2 21:44
     */
    public static SqlCommand getCommandById(Class<?> mapperClz, String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException(String.format("[%s]不是一个合法的编号，请检查。", id));
        }
        if (Objects.isNull(mapperClz)) {
            throw new IllegalArgumentException();
        }
        List<SqlCommand> sqlCommands = xmlStore.get(mapperClz);
        if (CollectionUtils.isEmpty(sqlCommands)) {
            throw new UnbindException(mapperClz.getName() + "并未绑定任何sql语句");
        }
        List<SqlCommand> sqlCommandList = sqlCommands.stream().filter(sqlCommand -> sqlCommand.getId().equals(id)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(sqlCommandList)) {
            throw new UnbindException(String.format("%s中编号为%s的mapper接口并未绑定任何sql语句", mapperClz.getName(), id));
        }
        return sqlCommandList.get(0);
    }
}
