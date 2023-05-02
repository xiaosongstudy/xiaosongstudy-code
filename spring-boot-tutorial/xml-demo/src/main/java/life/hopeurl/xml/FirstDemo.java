package life.hopeurl.xml;

import life.hopeurl.application.mysql.SqlCommand;
import life.hopeurl.application.mysql.XmlCache;
import life.hopeurl.application.mysql.example.mapper.UserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * xml解析第一个应用案例
 * 以下操作均基于dom4j进行处理
 *
 * @author shiping.song
 * @date 2023/5/1 22:47
 */
public class FirstDemo {


    @Test
    public void readXml01() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(FirstDemo.class.getResourceAsStream("/first-demo.xml"));
        Element root = document.getRootElement();

        // iterate through child elements of root
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            // do something
            for (Iterator<Attribute> attributeIt = element.attributeIterator(); attributeIt.hasNext(); ) {
                Attribute attribute = attributeIt.next();
                String name = attribute.getName();
                String value = attribute.getValue();
                System.out.printf("name:%s,value:%s\n", name, value);
            }
            Iterator<Element> childIterator = element.elementIterator();
            while (childIterator.hasNext()) {
                Element childElement = childIterator.next();
                // do something
                for (Iterator<Attribute> attributeIt = childElement.attributeIterator(); attributeIt.hasNext(); ) {
                    Attribute attribute = attributeIt.next();
                    String name = attribute.getName();
                    String value = attribute.getValue();
                    System.out.printf("name:%s,value:%s\n", name, value);
                }
                System.out.println(childElement.getText());
            }

        }
    }


    @Test
    public void readXml02() {
        XmlCache.beginCache();
        List<SqlCommand> sqlCommandList = XmlCache.getSqlCommandList(UserMapper.class);
        if (CollectionUtils.isNotEmpty(sqlCommandList)) {
            sqlCommandList.forEach(System.out::println);
        }
    }
}
