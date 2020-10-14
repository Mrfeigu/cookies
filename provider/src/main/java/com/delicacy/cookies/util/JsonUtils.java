package com.delicacy.cookies.util;

import io.micrometer.core.instrument.util.StringEscapeUtils;

/**
 * json 去转移字符
 *
 * @author linzhenghui
 * @date 2020/10/12
 */
public class JsonUtils {


    public static String removeEscaping(String jsonStr){
        return StringEscapeUtils.escapeJson(jsonStr);
    }

}
