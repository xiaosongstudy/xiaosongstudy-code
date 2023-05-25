package life.hopeurl;

import java.util.HashSet;
import java.util.Set;

/**
 * monitor测试
 *
 * @author shiping.song
 * @date 2023/5/25 22:43
 */
public class MonitorTest {

    private static Set<String> cacheSet = new HashSet<>();

    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + cache());
            }).start();
        }
    }

    private static String cache() {
        boolean cache = cacheSet.contains("a");
        if (!cache) {
            synchronized (MonitorTest.class) {
                cache = cacheSet.contains("a");
                if (!cache) {
                    cacheSet.add("a");
                }
            }
        }
        return "a";
    }
}
