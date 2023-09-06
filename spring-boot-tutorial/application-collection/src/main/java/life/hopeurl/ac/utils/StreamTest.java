package life.hopeurl.ac.utils;

import life.hopeurl.ac.demo.Demo;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流测试
 *
 * @author shiping.song
 * @date 2023/8/28 23:43
 * @email 2453332538@qq.com
 */
@Slf4j
public class StreamTest {

    public static void main(String[] args) {
        List<Demo> demos = Arrays.asList(
                new Demo(1, "zhangsan", LocalDate.now(),
                        Collections.singletonList(new Demo(101, "zhangsan的儿子", LocalDate.now(), null))),
                new Demo(3, "lisi", LocalDate.now().plusDays(30),
                        Collections.singletonList(new Demo(301, "lisi的儿子", LocalDate.now(), null))),
                new Demo(2, "wangwu", LocalDate.now().plusDays(30),
                        Collections.singletonList(new Demo(201, "wangwu的儿子", LocalDate.now(), null))),
                new Demo(14, "zhaoliu", LocalDate.now().plusDays(30),
                        Collections.singletonList(new Demo(1401, "zhaoliu的儿子", LocalDate.now(), null)))
        );
//        groupBy(demos);
//        groupByConcurrent(demos);
//       test01(demos);
        // 这个方法的存在主要是为了支持调试
//        demos.stream().peek()
        Integer reduceResult = demos.stream().map(Demo::getId).filter(Objects::nonNull).reduce(1, (old, current) -> {
            System.out.println(old + ":" + current);
            return old + current;
        });
        System.out.println(reduceResult);
    }

    private static void test01(List<Demo> demos) {
        Double result = demos.stream().collect(Collectors.averagingInt(Demo::getId));
        System.out.println(result);
        System.out.println("=============================================================================");
        Double result1 = demos.stream().collect(Collectors.averagingDouble(Demo::getId));
        System.out.println(result1);
        System.out.println("=============================================================================");
        Double result2 = demos.stream().collect(Collectors.averagingLong(Demo::getId));
        System.out.println(result2);
        System.out.println("=============================================================================");
        System.out.println(demos.stream().filter(demo -> demo.getId() > 1).count());
        System.out.println("=============================================================================");
        // 只需要一部分字段
        Set<Integer> collect = demos.stream().collect(Collectors.mapping(Demo::getId, Collectors.toSet()));
        System.out.println(collect);
        System.out.println("=============================================================================");
        System.out.println(demos.stream().map(Demo::getId).collect(Collectors.toList()));
        System.out.println("=============================================================================");
        // 只收纳部分属性
        Map<Integer, List<LocalDate>> collect1 = demos.stream().collect(
                Collectors.groupingBy(Demo::getId,
                        Collectors.mapping(Demo::getBirthday, Collectors.toList())));
        collect1.forEach((id, birthdayList) -> {
            System.out.printf("id=%s, birthdayList=%s\n", id, birthdayList);
        });
        System.out.println("=============================================================================");
        Map<Integer, LocalDate> idToBirthday = demos.stream().collect(Collectors.toMap(Demo::getId, Demo::getBirthday));
        idToBirthday.forEach((id, birthday) -> {
            System.out.printf("id=%s, birthday=%s\n", id, birthday);
        });
        System.out.println("=============================================================================");
        boolean allMatch = demos.stream().allMatch(demo -> 14 == demo.getId());
        System.out.println(allMatch);
        System.out.println("=============================================================================");
        List<LocalDate> collect2 = demos.stream().flatMap(demo -> Stream.of(demo.getBirthday())).collect(Collectors.toList());
        System.out.println(collect2);
        System.out.println("=============================================================================");
        List<Demo> collect3 = demos.stream().flatMap(demo -> demo.getChildDemo().stream()).collect(Collectors.toList());
        collect3.forEach(System.out::println);
        System.out.println("=============================================================================");
        int max = demos.stream().flatMapToInt(demo -> demo.getChildDemo().stream()
                .mapToInt(Demo::getId)).max().orElse(-1);
        System.out.println(max);
        System.out.println("=============================================================================");
    }

    private static void groupByConcurrent(List<Demo> demos) {
        long startTime = System.currentTimeMillis();
        ConcurrentMap<Integer, List<Demo>> collect = demos.stream().collect(Collectors.groupingByConcurrent(Demo::getId));
        collect.forEach((id, demoList) -> {
            System.out.printf("id=%s, demos=%s\n", id, demoList);
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private static void groupBy(List<Demo> demos) {
        // 1.分组条件，以默认收纳规则进行收纳，有key值相同的收纳进一个list组中 以某一个字段属性作为key此时可借助方法引用简写
        Map<Integer, List<Demo>> collect = demos.stream().collect(Collectors.groupingBy(Demo::getId));
        for (Map.Entry<Integer, List<Demo>> integerListEntry : collect.entrySet()) {
            System.out.printf("key=%s, value=%s\n", integerListEntry.getKey(), integerListEntry.getValue());
        }
        System.out.println("=============================================================================");
        // 1.2 多个属性构成分组key，可自行实现Function<? super T, ? extends K> classifier
        Map<String, List<Demo>> dayIdToDemos = demos.stream().collect(Collectors.groupingBy(demo -> demo.getId() + "--" + DateTimeFormatter.ofPattern("yyyyMMdd").format(demo.getBirthday())));
        for (Map.Entry<String, List<Demo>> dataMap : dayIdToDemos.entrySet()) {
            System.out.printf("key=%s, value=%s\n", dataMap.getKey(), dataMap.getValue());
        }
        System.out.println("=============================================================================");
        // 2. 自定义第二个入参：  Collector<? super T, A, D> downstream
        /*
        重写之前需要知道三个范型是啥
        1. T the type of the input elements 输入元素的范型，这里对应  Demo类型。
        2. A the intermediate accumulation type of the downstream collector 收纳下级流容器的范型
        3. D the result type of the downstream reduction  合并后的实际结果范型
        梳理 a范型和d范型时可以参照其默认实现toList
        return new CollectorImpl<>(ArrayList::new, List::add,
                                   (left, right) -> { left.addAll(right); return left; },
                                   CH_ID);
                        // 与上面对应
           CollectorImpl(Supplier<A> supplier,  //  提供一个空的集合
                      BiConsumer<A, T> accumulator, // 消费者，总共消费两个元素，一个是上游待处理的 demo， 一个是supplier提供的集合容器
                      BinaryOperator<A> combiner, // 组合器，如果有两个装 demo的 list容器，应该如何处理？默认实现是左边加上右边的内容
                      Set<Characteristics> characteristics) {  // 这个暂时不知道，当前咱不是重点
            this(supplier, accumulator, combiner, castingIdentity(), characteristics);
        }
         */
        // 先根据 id分组，然后根据生日分组
        Map<Integer, Map<String, List<Demo>>> idAfterBirthdayToDemo = demos.stream().collect(Collectors.groupingBy(Demo::getId,
                Collectors.groupingBy(demo -> DateTimeFormatter.ofPattern("yyyyMMdd").format(demo.getBirthday()))));
        idAfterBirthdayToDemo.forEach((id, birthdayToDemoMap) -> {
            birthdayToDemoMap.forEach((birthday, demoList) -> {
                System.out.printf("id编号为：%s, 生日为：%s，符合两个分组条件的收纳的信息数据为：%s\n", id, birthday, demoList);
            });
        });
        System.out.println("=============================================================================");
    }

    public <T> void doBusiness(T businessParams, Consumer<T> businessHandler) {
        try {
            businessHandler.accept(businessParams);
        } catch (Exception e) {
            log.error("业务处理异常", e);
        }
    }
}
