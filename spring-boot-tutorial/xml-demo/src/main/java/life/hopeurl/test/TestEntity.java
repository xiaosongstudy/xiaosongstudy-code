package life.hopeurl.test;

import life.hopeurl.application.mysql.entity.User;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

/**
 * 测试类
 *
 * @author shiping.song
 * @date 2023/5/20 13:52
 */
public class TestEntity {

    public void printParam(int age, String username) {
        // nothing to do
    }

    public void printParam(User user) {
        // nothing to do
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method printParam = TestEntity.class.getMethod("printParam", User.class);
        System.out.println(printParam.getName());
        Parameter[] parameters = printParam.getParameters();
        Stream.of(parameters).forEach(v -> {
            System.out.println("type=" + v.getType().getName() + ", name=" + v.getName());
        });
    }
}
