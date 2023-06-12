package life.hopeurl;

/**
 * 测试数据.<br>
 *
 * @author shiping.song
 * @date 2023/6/9 11:21
 */
public class TestDemo {


    public static void main(String[] args) {
        String a = "v";
        System.out.println(a.hashCode());
        change(a);
    }

    private static void change(String a) {
        System.out.println(a.hashCode());
        a = "zhangsan";
        System.out.println(a.hashCode());
    }
}
