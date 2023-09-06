package life.hopeurl.ac.demo;

import java.util.function.Function;

/**
 * @author shiping.song
 * @date 2023/8/31 00:30
 * @email 2453332538@qq.com
 */
@FunctionalInterface
public interface Demo02 {

    void doIt();

    default void doOther(Function<String, String> mapper) {
        mapper.apply("a");
    }
}
