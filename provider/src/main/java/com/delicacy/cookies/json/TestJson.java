package com.delicacy.cookies.json;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linzhenghui
 * @date 2020/7/13
 */
public class TestJson {


    public static void main(String[] args) {

        // String str = "{\"id\":1,\"userMessage\":{\"name\":\"张三\",\"age\":11}}";
        String str = null;
        Map<String, Object> maps = new HashMap<>();
        Map o = (Map)JSON.parse(str);
        maps.put("log", o);

        String s = JSON.toJSONString(maps);

        Map mp1 = (Map)JSON.parse(s);

        System.out.println("ending..");

    }

    @Data
    public static class TestDemo implements Serializable {

        private String flag;

        private Map<String, Object> maps;
    }

}
