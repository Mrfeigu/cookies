package com.delicacy.cookies.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * http://ifeve.com/google-guava-cachesexplained/
 * LocalCache
 *
 * 适用场景：
 * 1. 你愿意消耗一些内存空间来提升速度
 * 2. 你预料到某些键会被查询一次以上。
 * 3. 缓存中存放的数据总量不会超出内存容量。
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class LoadingCacheUtils {

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()

            .build(
                    new CacheLoader<String, String>() {
                        /**
                         * @param key
                         * @return
                         * @throws Exception 这里有个异常抛出，如果声明了非受查异常，可以 使用 cache.getUnchecked("1");
                         */
                        @Override
                        public String load(String key) throws Exception {
                            // 返回value的规则
                            return key.toUpperCase();
                        }

                        /**
                         * getAll(Iterable<? extends K>)方法用来执行批量查询。
                         * 默认情况下，对每个不在缓存中的键，getAll方法会单独调用CacheLoader.load来加载缓存项。
                         * 如果批量的加载比多个单独加载更高效，你可以重载CacheLoader.loadAll来利用这一点。getAll(Iterable)的性能也会相应提升。
                         *
                         * @param keys
                         * @return
                         * @throws Exception
                         */
                        @Override
                        public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                            return Lists.newLinkedList(keys).stream().collect(Collectors.toMap(e -> e, String::toUpperCase, (o, n) -> n));
                        }

                    }
            );


    public static void main(String[] args) throws ExecutionException {

        // 使用cache.put(key, value)方法可以直接向缓存中插入值，这会直接覆盖掉给定键之前映射的值。
        // 使用Cache.asMap()视图提供的任何方法也能修改缓存。但请注意，asMap视图的任何方法都不能保证缓存项被原子地加载到缓存中。
        // 进一步说，asMap视图的原子运算在Guava Cache的原子加载范畴之外，所以相比于Cache.asMap().putIfAbsent(K,V)，Cache.get(K, Callable<V>) 应该总是优先使用。

        // 理论上是不应该用视图的map
        cache.put("1", "String");

        // LoadingCache.get(K)也声明为抛出ExecutionException异常
        String s = cache.get("1");
        String unchecked = cache.getUnchecked("1");
        // todo 这个不懂有啥用
        String s1 = cache.get("1", () -> "1" + "?");









    }




}
