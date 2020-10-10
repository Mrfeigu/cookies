package com.delicacy.cookies.base;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

/**
 * Preconditions感觉是最没用的api了
 * @author linzhenghui
 * @date 2020/10/10
 */
public class GuavaBaseUtils {

    public static void prepositionCondition(){
        // 判空，抛出NullPointerException
        String s = null;
        Preconditions.checkNotNull(s);
        // 检查表达式，抛出IllegalArgumentException
        Preconditions.checkArgument(1 < 2 );
        // 检查表达式，抛出IllegalArgumentException
        Preconditions.checkState(1 < 2, "这是异常信息");
        // 检查是否列表，数组类型是否溢出
        Preconditions.checkElementIndex(1, 10);
        // 检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *, 抛出IndexOutOfBoundsException
        Preconditions.checkPositionIndex(1,10);
        // 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*, 抛出IndexOutOfBoundsException
        Preconditions.checkPositionIndexes(1,2,10);
    }

    public static void commonObject(){
        // 有效判断，可以避免null判断
        boolean equal = Objects.equal("null", "大哥大嫂过年好1");
    }



    public static void main(String[] args) {
    }

}
