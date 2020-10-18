package com.delicacy.cookies.mult;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 * http://ifeve.com/google-guava-newcollectiontypes/
 * ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class ClassToInstanceMapUtils {

    /**
     * MutableClassToInstanceMap, ImmutableClassToInstanceMap
     */
    private static ClassToInstanceMap<Object> classToInstanceMap = MutableClassToInstanceMap.create();

    public static void main(String[] args) {

        classToInstanceMap.put(Integer.class, 1);
        classToInstanceMap.put(String.class, "1");
        String instance = classToInstanceMap.getInstance(String.class);


        System.out.println("ending...");
    }



}
