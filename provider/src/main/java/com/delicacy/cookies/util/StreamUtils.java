package com.delicacy.cookies.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author linzhenghui
 * @date 2020/7/31
 */
public class StreamUtils {

    /*********************************************List转换****************************************************/


    /**
     * 普通 List to List
     * @param list data
     * @param mapper mapper function
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P> List<R> listToList(List<P> list, Function<? super P, ? extends R> mapper){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * List to List 带正序
     * @param list data
     * @param mapper mapper function
     * @param keyExtractor sort function 排序字段的get方法
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P extends Comparable<? super P>> List<R> listToListSort(
            List<P> list,
            Function<? super P, ? extends R> mapper
            , Function<? super R, ? extends P> keyExtractor)
    {
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .map(mapper)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(keyExtractor))
                .collect(Collectors.toList());
    }

    /**
     * List to List 带逆序
     * @param list data
     * @param mapper mapper function
     * @param keyExtractor sort function 排序字段的get方法
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P extends Comparable<? super P>> List<R> listToListSortReversed(
            List<P> list,
            Function<? super P, ? extends R> mapper
            , Function<? super R, ? extends P> keyExtractor)
    {
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .map(mapper)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(keyExtractor).reversed())
                .collect(Collectors.toList());
    }

    /**
     * 普通 List to List 带过滤方法
     * @param list data
     * @param mapper mapper function
     * @param predicate predicate function 过滤方法
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P> List<R> listToListFilter(List<P> list,
                                                 Function<? super P, ? extends R> mapper,
                                                 Predicate<? super P> predicate){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(predicate)
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * List Filter
     * @param list data
     * @param predicate 过滤方法
     * @param <R> 返回类型
     * @return List<R>
     */
    public static<R> List<R> listFilter(List<R> list, Predicate<? super R> predicate){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(predicate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * List根据字段去重
     * @param list data
     * @param keyExtractor 去重字段get方法
     * @param <R> 结果
     * @param <U> 去重字段
     * @return list<R>
     */
    public static<R, U extends Comparable<? super U>> List<R> listRemoveRepeat(
            List<R> list
            , Function<? super R, ? extends U> keyExtractor){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() ->
                                        new TreeSet<>(Comparator.comparing(keyExtractor))
                                ), ArrayList::new
                        )
                );
    }

    /**
     * list 转 set
     * @param list
     * @param <R>
     * @return
     */
    public static<R> Set<R> listToSet(List<R> list){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * List 转 map, (key有重复，取遍历最新的)
     * @param list data
     * @param keyMapper key
     * @param valueMapper value
     * @param <K> k
     * @param <V> v
     * @param <R> r
     * @return Map<K, V>
     */
    public static<K, V, R> Map<K, V> listToMap(List<R> list
            , Function<? super R, ? extends K> keyMapper
            , Function<? super R, ? extends V> valueMapper){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(keyMapper, valueMapper, (o, n) -> n));
    }

    /**
     * list to group list
     * @param list data
     * @param classifier grouping function
     * @param <K> k
     * @param <R> r
     * @return Map<K, List<R>>
     */
    public static <K, R> Map<K, List<R>> listToMapGroup(List<R> list, Function<? super R, ? extends K> classifier){
        Objects.requireNonNull(list);
        return list
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(classifier));
    }

    /*********************************************Set转换****************************************************/
    // todo

    public static void main(String[] args){







    }



}
