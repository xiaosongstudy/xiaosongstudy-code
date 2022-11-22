package com.gitee.xiaosongstudy.security.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 这是类注释.<br>
 *
 * @author shiping.song
 * @date 2022/11/5 22:50
 */
public class JsonObjectTest {

    public static void main(String[] args) {
        Demo01 demo01 = Demo01.builder().name("张三").id(1212).build();
        JSONObject data = new JSONObject();
        data.put("demo01", demo01);
        String result = JSON.toJSONString(data, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(result);
    }
}
