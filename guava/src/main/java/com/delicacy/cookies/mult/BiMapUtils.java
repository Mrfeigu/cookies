package com.delicacy.cookies.mult;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * http://ifeve.com/google-guava-newcollectiontypes/
 *
 * key value 反转map
 * BiMap<K, V>是特殊的Map：
 * 可以用 inverse()反转BiMap<K, V>的键值映射
 * 保证值是唯一的，因此 values()返回Set而不是普通的Collection
 *
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class BiMapUtils {

    /**
     * HashBiMap, ImmutableBiMap, EnumBiMap, EnumHashBiMap
     */
    private static BiMap<String, String> biMap = HashBiMap.create();

    public static void main(String[] args) {

        biMap.put(null, null);
        biMap.put("duo", "duo");

        // 同key会被覆盖
        biMap.put("叶惠美", "七里香");
        biMap.put("叶惠美", "晴天");

        // 两极反转，冲
        BiMap<String, String> inverse = biMap.inverse();

        System.out.println("ending...");
    }



}
