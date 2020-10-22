package com.delicacy.cookies.testdemo;

import com.delicacy.cookies.entity.UserInfo;
import com.delicacy.cookies.util.StreamUtils;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author linzhenghui
 * @date 2020/10/21
 */
public class teststream {

    public static void main(String[] args) {
        List<UserInfo> userList = Lists.newArrayList();
        Map<String, UserInfo> stringUserInfoMap = StreamUtils.listToMap(userList, UserInfo::getName, e -> e);
        UserInfo ahh = stringUserInfoMap.get("ahh");
        System.out.println("ending...");
    }


}
