package com.gitee.xiaosongstudy.sourcetrack.chapter002;

import org.springframework.stereotype.Component;

/**
 * 测试类.<br>
 *
 * @author shiping.song
 * @date 2022/10/22 20:25
 */
@Component
public class C002Bean {

    private int id;

    private String name;

    public C002Bean() {
        this(202210, "C002Bean");
        System.out.println("C002Bean @Component 初始化成功...");
    }

    public C002Bean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "C002Bean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
