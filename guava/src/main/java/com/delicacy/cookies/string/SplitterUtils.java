package com.delicacy.cookies.string;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * 字符串处理分割器
 * @author linzhenghui
 * @date 2020/10/21
 */

public class SplitterUtils {


    public static void main(String[] args) {

//        工厂类
//        Splitter.on(char)
//        Splitter.on(CharMatcher)
//        Splitter.on(String)
//        Splitter.on(Pattern)
//        Splitter.onPattern(String)
//        Splitter.fixedLength(int)

        String str = "ABCDEFGCWDNMDDNFCGGHCFGcND";
        List<String> c = Splitter.on('C')
                // 移除结果串中的空白字符
                .trimResults()
                // 省略空值
                .omitEmptyStrings()
                .splitToList(str);

        System.out.println("ending...");

    }


}
