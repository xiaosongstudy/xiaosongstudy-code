package com.gitee.xiaosongstudy.request.test;

import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.request.bean.User;
import com.gitee.xiaosongstudy.request.jdk.RemoteInvocationUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * remoteInvocation工具类测试.<br>
 *
 * @author shiping.song
 * @date 2022/10/17 11:03
 */
public class RemoteInvocationUtilTest {

    public static void main(String[] args) {
        test07();
    }


    /**
     *  post表单上传文件
     * @author shiping.song
     * @date 2022/10/17 15:33
     */
    private static void test07() {
        Map<String, Object> paramsMap = new HashMap<>(1);
        paramsMap.put("username", "admin");
        System.out.println(RemoteInvocationUtil.formForEntity("http://localhost:8081/file/formUploadFileItem", new File("E:\\01项目\\02测试素材\\认证图片\\1.jpg"), paramsMap, String.class));
    }

    /**
     * getForEntity 没有参数
     *
     * @author shiping.song
     * @date 2022/10/17 11:12
     */
    private static void test01() {
        // 返回正常
        RemoteInvocationUtil.getForEntity("http://localhost:9022/request/sayHello", null);
    }

    /**
     * getForEntity 有参数
     *
     * @author shiping.song
     * @date 2022/10/17 11:13
     */
    private static void test02() {
        // 返回正常
        User forEntity = RemoteInvocationUtil.getForEntity("http://localhost:9022/request/getJsonStr", User.class);
        System.out.println(forEntity);
    }


    /**
     * getForEntity 有get路径参数
     *
     * @author shiping.song
     * @date 2022/10/17 11:13
     */
    private static void test03() {
        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("id", 1);
        pathVariables.put("username", "张三");
        // 在进行时间转化时后台需要额外做配置
//        pathVariables.put("birthday", LocalDate.now());
        System.out.println(RemoteInvocationUtil.getForEntity("http://localhost:9022/request/param", pathVariables, User.class));
        System.out.println(RemoteInvocationUtil.getForEntity("http://localhost:9022/request/paramWithEntity", pathVariables, User.class));
        System.out.println(RemoteInvocationUtil.getForEntity("http://localhost:9022/request/param?id=1&username=%E5%BC%A0%E4%B8%89", null, User.class));
    }

    /**
     * postForLocation 无参数、无返回值
     *
     * @author shiping.song
     * @date 2022/10/17 11:15
     */
    private static void test04() {
        Map<String, Object> postPathVariables = new HashMap<>(1);
        postPathVariables.put("param", "我是测试数据");
        RemoteInvocationUtil.postForLocation("http://localhost:9022/request/postStr", postPathVariables);
    }

    /**
     * postForLocation 有参数有返回值
     *
     * @author shiping.song
     * @date 2022/10/17 11:16
     */
    private static void test05() {
        User user = User.builder().id(20221010).username("里斯").build();
        System.out.println(RemoteInvocationUtil.postForEntity("http://localhost:9022/request/postWithEntity", JSON.toJSONString(user), String.class));
    }

    /**
     * 上传附件
     *
     * @author shiping.song
     * @date 2022/10/17 15:32
     */
    private static void test06() {
        System.out.println(Objects.requireNonNull(RemoteInvocationUtil.class.getClassLoader().getResource("")).getPath());
        System.out.println(RemoteInvocationUtil.postAttachment("http://localhost:9022/request/upload", new File("E:\\认证图片\\平台授权说明书_看图王.jpg")));
//        test01();
    }
}
