package com.delicacy.cookies.util;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author linzhenghui
 * @date 2020/9/3
 */
public class BatchUtils {

    /** 默认分批阈值*/
    private static final int DEFAULT_THRESHOLD = 100;

    /**
     * 分批执行
     * @param list 列表
     * @param customThreshold 自定义阈值
     * @param action 执行方法
     * @param <T>
     */
    public static <T> void exe(List<T> list, int customThreshold, Consumer<? super List<T>> action){

        if(list == null || list.isEmpty()) {
            return;
        }

        int threshold = customThreshold == 0 ? DEFAULT_THRESHOLD : customThreshold;
        int size = list.size();
        int count = (size - 1) / threshold + 1;

        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * threshold, (Math.min((i + 1) * threshold, size)));
            action.accept(subList);
        }

    }


}
