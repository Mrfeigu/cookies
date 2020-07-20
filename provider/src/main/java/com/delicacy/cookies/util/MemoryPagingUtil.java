package com.delicacy.cookies.util;

import java.util.ArrayList;
import java.util.List;

public class MemoryPagingUtil {

    public static <T> List<T> paging(List<T> list, int page, int pageSize){
        int begin = page <= 1 ? 0 : (page - 1) * pageSize;
        int end = Math.min(begin + pageSize, list.size());
        if(begin >= end){
            return new ArrayList<T>();
        }
        return list.subList(begin, end);
    }

}