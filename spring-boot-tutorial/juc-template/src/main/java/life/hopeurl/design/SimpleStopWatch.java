package life.hopeurl.design;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 简易计时器
 *
 * @author shiping.song
 * @date 2023/5/26 00:34
 */
public class SimpleStopWatch {

    /**
     * 开始时间
     */
    private final long beginTime;

    /**
     * 结束时间
     */
    private long endTime;

    /**
     * 主题
     */
    private String topic;

    /**
     * 自定义日志
     */
    private String log;


    public SimpleStopWatch(long beginTime, String topic) {
        this.beginTime = beginTime;
        this.topic = topic;
    }

    private SimpleStopWatch(long beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String toString() {
        String defaultLog = String.format("%s总耗时：%sms", topic, endTime - beginTime);
        return Objects.isNull(log) || log.trim().isEmpty() ? defaultLog : log;
    }

    public SimpleStopWatch log(String log) {
        this.log = log;
        return this;
    }

    public static SimpleStopWatch start(String topic) {
        WeakReference<SimpleStopWatch> weakReference = new WeakReference<>(new SimpleStopWatch(System.currentTimeMillis(), topic));
        return weakReference.get();
    }

    public void end(Consumer<SimpleStopWatch> stopWatchConsumer) {
        if (Objects.nonNull(stopWatchConsumer)) {
            stopWatchConsumer.accept(this);
        } else {
            endTime = System.currentTimeMillis();
        }
        System.out.println(this);
    }

    public void end() {
        end(null);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleStopWatch simpleStopWatch = SimpleStopWatch.start("解析业务预报表");
        TimeUnit.MILLISECONDS.sleep(200);
        simpleStopWatch.end(watch -> watch.log(">>>>>"));
    }
}
