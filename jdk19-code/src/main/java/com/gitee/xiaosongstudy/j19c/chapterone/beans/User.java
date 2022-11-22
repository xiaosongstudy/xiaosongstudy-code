package com.gitee.xiaosongstudy.j19c.chapterone.beans;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户类.<br>
 *
 * @author shiping.song
 * @date 2022/11/22 1:21
 */
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -4291843713039877574L;

    private int age;

    private String username;

    public User(int age, String username) {
        this.age = age;
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", username='" + username + '\'' +
                '}';
    }
}
