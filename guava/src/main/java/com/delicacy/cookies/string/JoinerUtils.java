package com.delicacy.cookies.string;

import com.google.common.base.Joiner;

/**
 * 字符串处理连接器
 * http://ifeve.com/google-guava-strings/
 *
 * @author linzhenghui
 * @date 2020/10/21
 */
public class JoinerUtils {


    public static void main(String[] args) {

        Joiner joiner = Joiner.on(",").skipNulls();
        String join = joiner.join("黄金时代", null, "王小波");
        System.out.println(join);

    }

}
