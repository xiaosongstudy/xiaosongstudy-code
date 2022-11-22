package com.gitee.xiaosongstudy.j19c.chapterone;

/**
 * jdk19 初体验.<br>
 *
 * @author shiping.song
 * @date 2022/11/22 1:16
 */
public class FirstDemo {

    public static void main(String[] args) {
//        var obj = "123";
//        var userList = List.of(new User(20221101, "shiping.song"));
//        userList.forEach(System.out::println);
        switchDemo("21");
    }

    private static void switchDemo(String invoiceType) {
        var translateResult = switch (invoiceType) {
            case "21", "22" -> "全电发票";
            default -> throw new IllegalArgumentException();
        };
        System.out.println(translateResult);
    }

    private static void demo02() {

    }
}
