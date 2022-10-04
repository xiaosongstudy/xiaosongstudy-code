package com.gitee.xiaosongstudy.security.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/4 0:27
 */
@Getter
@Setter
@ToString
public class BeanDemo {

    private Integer id;

    private String version;

    List<Demo01> child;
}
