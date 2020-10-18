package com.delicacy.cookies.mult;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

/**
 * Table 两个key索引
 *
 * Table，它有两个支持所有类型的键：”行”和”列”。
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class TableUtils {

    /**
     * HashBasedTable 本质上用HashMap<R, HashMap<C, V>>实现
     * TreeBasedTable 本质上用TreeMap<R, TreeMap<C,V>>实现
     * ImmutableTable 本质上用ImmutableMap<R, ImmutableMap<C, V>>实现；注：ImmutableTable对稀疏或密集的数据集都有优化。
     * ArrayTable 要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集Table的内存利用率。ArrayTable与其他Table的工作原理有点不同
     */
    private static Table<String, String, String> table = HashBasedTable.create();

    public static void main(String[] args) {

        table.put("jay", "叶惠美", "晴天");
        table.put("jay", "叶惠美", "七里香");
        table.put("jay", "魔羯座", "龙拳");

        table.put("eason", "duo", "今天等我来");
        table.put("eason", "duo", "好歌献给你");
        table.put("eason", "duo", "喜帖街");

        table.put("eason", "h3m", "阿牛");
        table.put("eason", "h3m", "不来也不去");

        /** key1, key2, value 都不能为null
            table.put(null, "test", "test");
            table.put("key", null, "test");
            table.put("null", "null", null);
         */

        String s = table.get("eason", "duo");

        Map<String, String> eason = table.row("eason");
        Map<String, Map<String, String>> stringMapMap = table.rowMap();

        Set<Table.Cell<String, String, String>> cells = table.cellSet();

        System.out.println("ending...");
    }



}
