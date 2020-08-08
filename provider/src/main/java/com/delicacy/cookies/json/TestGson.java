package com.delicacy.cookies.json;

import com.delicacy.cookies.entity.Detail;
import com.delicacy.cookies.entity.UserInfo;
import com.google.gson.Gson;

/**
 * @author linzhenghui
 * @date 2020/7/27
 */
public class TestGson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Detail detail = new Detail(123, "好人");
        UserInfo userInfo = new UserInfo( 12, "小明", detail);
        String s = gson.toJson(userInfo);
        System.out.println(s);
        System.out.println("ending...");
    }



}
