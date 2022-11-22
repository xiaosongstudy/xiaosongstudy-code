package com.gitee.xiaosongstudy.security.test;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例1<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/3 21:48
 */
@Builder
public class Demo01 {

    private Integer id;
    private String name;

    public Demo01(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        List<Demo01> list = new ArrayList<>(3);
        list.add(new Demo01(123,"a"));
        list.add(new Demo01(234,"b"));
        list.add(new Demo01(345,"c"));
        BeanDemo beanDemo = new BeanDemo();
        beanDemo.setChild(list);
        beanDemo.setVersion("1.0.0");
        beanDemo.setId(111);
        String str = JSON.toJSONString(beanDemo);
        System.out.println(str);
        BeanDemo beanDemo1 = JSON.parseObject(str, BeanDemo.class);
        System.out.println(beanDemo1);
    }
}
