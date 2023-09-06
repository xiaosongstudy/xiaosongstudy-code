package life.hopeurl.ac.utils;

/**
 * @author shiping.song
 * @date 2023/8/31 23:33
 * @email 2453332538@qq.com
 */
public class TestDemo02 {

    public static void main(String[] args) {
        // 2986093  2986092
        String a = "aaab";
        int h;
        System.out.println((h = a.hashCode()) ^ (h >>> 16));
    }
}
