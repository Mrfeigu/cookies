package com.delicacy.cookies.util;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * stream工具
 * @author linzhenghui
 * @date 2020/7/31
 */
public class StreamUtils {

    /**
     * Collection to List
     * @param collection data
     * @param mapper mapper function
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P> List<R> toList(Collection<P> collection, Function<? super P, ? extends R> mapper){
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(Objects::nonNull)
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Collection to List 带正序
     * @param collection data
     * @param mapper mapper function
     * @param keyExtractor sort function 排序字段的get方法
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static <P , R, U extends Comparable<? super U>> List<R> toListSort(
            Collection<P> collection,
            Function<? super P, ? extends R> mapper
            , Function<? super P, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(keyExtractor))
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Collection to List 带逆序
     * @param collection data
     * @param mapper mapper function
     * @param keyExtractor sort function 排序字段的get方法
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P, U extends Comparable<? super U>> List<R> listToListSortReversed(
            Collection<P> collection,
            Function<? super P, ? extends R> mapper
            , Function<? super P, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(keyExtractor).reversed())
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 普通 Collection to List 带过滤方法
     * @param collection data
     * @param mapper mapper function
     * @param predicate predicate function 过滤方法
     * @param <R> result
     * @param <P> param
     * @return List<R>
     */
    public static<R, P> List<R> toListFilter(Collection<P> collection,
                                                 Function<? super P, ? extends R> mapper,
                                                 Predicate<? super P> predicate){
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(predicate)
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Collection Filter
     * @param collection data
     * @param predicate 过滤方法
     * @param <R> 返回类型
     * @return List<R>
     */
    public static<R> List<R> listFilter(Collection<R> collection, Predicate<? super R> predicate){
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(predicate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Collection根据字段去重
     * @param collection data
     * @param keyExtractor 去重字段get方法
     * @param <R> 结果
     * @param <U> 去重字段
     * @return list<R>
     */
    public static<R, U extends Comparable<? super U>> List<R> listRemoveRepeat(
            Collection<R> collection
            , Function<? super R, ? extends U> keyExtractor){
        Objects.requireNonNull(collection);
        return collection
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
     * Collection 转 set
     * @param collection data
     * @param <R> r
     * @return Set<R>
     */
    public static<R> Set<R> toSet(Collection<R> collection){
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * Collection 转 map, (key有重复，取遍历最新的)
     * @param collection data
     * @param keyMapper key
     * @param valueMapper value
     * @param <K> k
     * @param <V> v
     * @param <R> r
     * @return Map<K, V>
     */
    public static<K, V, R> Map<K, V> listToMap(Collection<R> collection
            , Function<? super R, ? extends K> keyMapper
            , Function<? super R, ? extends V> valueMapper){
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(keyMapper, valueMapper, (o, n) -> n));
    }

    /**
     * Collection to group list
     * @param collection data
     * @param classifier grouping function
     * @param <K> k
     * @param <R> r
     * @return Map<K, List<R>>
     */
    public static <K, R> Map<K, List<R>> listToMapGroup(Collection<R> collection, Function<? super R, ? extends K> classifier){
        Objects.requireNonNull(collection);
        return collection
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(classifier));
    }


    /*********************************************需要guava包支持*********************************************/


    /**
     * 转guava Multimap
     * @param data List
     * @param keyExtractor  keyExtractor
     * @param valueExtractor valueExtractor
     * @param <T> t
     * @param <K> k
     * @param <V> v
     * @return Multimap<K, V>
     */
    public static <T, K, V> Multimap<K, V> toMultimap(
            List<T> data,
            Function<? super T, ? extends K> keyExtractor,
            Function<? super T, ? extends V> valueExtractor) {
        return StreamUtils.toMultimap(data, LinkedListMultimap::create, keyExtractor, valueExtractor);
    }

    /**
     * 转guava Multimap 需要guava包支持
     * @param data list
     * @param containerFactory Multimap实例创建
     * @param keyExtractor key
     * @param valueExtractor value
     * @param <T> T
     * @param <K> K
     * @param <V> V
     * @return Multimap<K, V>
     */
    public static <T, K, V> Multimap<K, V> toMultimap(
            List<T> data,
            Supplier<Multimap<K, V>> containerFactory,
            Function<? super T, ? extends K> keyExtractor,
            Function<? super T, ? extends V> valueExtractor) {
        if (data == null || data.isEmpty()) {
            return LinkedListMultimap.create();
        }
        return data
                .stream()
                .filter(Objects::nonNull)
                .collect(new MultimapCollector<>(containerFactory, keyExtractor, valueExtractor));
    }


    /**
     * 构造者
     * @param <T>
     * @param <K>
     * @param <V>
     */
    private static class MultimapCollector<T, K, V> implements Collector<T, Multimap<K, V>, Multimap<K, V>> {

        private Supplier<Multimap<K, V>> containerFactory;
        private Function<? super T, ? extends K> keyExtractor;
        private Function<? super T, ? extends V> valueExtractor;

        MultimapCollector(Supplier<Multimap<K, V>> containerFactory
                , Function<? super T, ? extends K> keyExtractor
                , Function<? super T, ? extends V> valueExtractor) {
            Objects.requireNonNull(containerFactory, "require non null containerFactory");
            Objects.requireNonNull(keyExtractor, "require non null keyExtractor");
            Objects.requireNonNull(valueExtractor, "require non null valueExtractor");
            this.containerFactory = containerFactory;
            this.keyExtractor = keyExtractor;
            this.valueExtractor = valueExtractor;
        }

        @Override
        public Supplier<Multimap<K, V>> supplier() {
            return containerFactory;
        }

        @Override
        public BiConsumer<Multimap<K, V>, T> accumulator() {
            return (multimap, item) -> multimap.put(keyExtractor.apply(item), valueExtractor.apply(item));
        }

        @Override
        public BinaryOperator<Multimap<K, V>> combiner() {
            return (kvMultimapA, kvMultimapB) -> {
                kvMultimapA.putAll(kvMultimapB);
                return kvMultimapA;
            };
        }

        @Override
        public Function<Multimap<K, V>, Multimap<K, V>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
        }

    }


}
