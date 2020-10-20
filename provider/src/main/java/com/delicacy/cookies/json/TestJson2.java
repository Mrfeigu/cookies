package com.delicacy.cookies.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @author linzhenghui
 * @date 2020/10/20
 */
public class TestJson2 {


    public static void main(String[] args) {

        String text = "{\"a\":0.33865234532654534534523346565452342342342343242336565465645666666666666666666666666666666666666666666666666666666666666666666666661}";
        JSONObject jsonObject = JSONObject.parseObject(text);
        double a = jsonObject.getDouble("a") * 1000;
        int i = (int) a;

        double c = -1D;
        int d = (int)c;

        System.out.println("ending...");

    }



}
