package com.delicacy.cookies.cache;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * http://ifeve.com/google-guava-cachesexplained/
 *
 * LocalCache
 *
 * 适用场景：
 * 1. 你愿意消耗一些内存空间来提升速度
 * 2. 你预料到某些键会被查询一次以上。
 * 3. 缓存中存放的数据总量不会超出内存容量。
 *
 * 缓存清理
 * 使用CacheBuilder构建的缓存不会"自动"执行清理和回收工作，也不会在某个缓存项过期后马上清理，也没有诸如此类的清理机制。
 * 相反，它会在写操作时顺带做少量的维护工作，或者偶尔在读操作时做——如果写操作实在太少的话。
 * 这样做的原因在于：如果要自动地持续清理缓存，就必须有一个线程，这个线程会和用户操作竞争共享锁。此外，某些环境下线程创建可能受限制，这样CacheBuilder就不可用了。
 * 相反，我们把选择权交到你手里。如果你的缓存是高吞吐的，那就无需担心缓存的维护和清理等工作。
 * 如果你的 缓存只会偶尔有写操作，而你又不想清理工作阻碍了读操作，那么可以创建自己的维护线程，以固定的时间间隔调用Cache.cleanUp()。ScheduledExecutorService可以帮助你很好地实现这样的定时调度。
 *
 *
 * @author linzhenghui
 * @date 2020/10/18
 */
public class LoadingCacheUtils {

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            // 规定缓存项的数目不超过固定值
            .maximumSize(50)
            // 最大重
            .maximumWeight(100000)
            // 判断值重
            .weigher(new Weigher<String, String>() {
                @Override
                public int weigh(String key, String value) {
                    return value.length();
                }
            })
            // 缓存项在给定时间内没有被读/写访问，则回收。
            .expireAfterAccess(100, TimeUnit.SECONDS)
            // 缓存项在给定时间内没有被写访问（创建或覆盖），则回收。
            .expireAfterWrite(100, TimeUnit.SECONDS)
            // 用弱引用存储键。当键没有其它（强或软）引用时，缓存项可以被垃圾回收。因为垃圾回收仅依赖恒等式（==），使用弱引用键的缓存用==而不是equals比较键。
            .weakKeys()
            // 使用弱引用存储值。当值没有其它（强或软）引用时，缓存项可以被垃圾回收。因为垃圾回收仅依赖恒等式（==），使用弱引用值的缓存用==而不是equals比较值。
            .weakValues()
            // 使用软引用存储值。软引用只有在响应内存需要时，才按照全局最近最少使用的顺序回收。考虑到使用软引用的性能影响，我们通常建议使用更有性能预测性的缓存大小限定（见上文，基于容量回收）。使用软引用值的缓存同样用==而不是equals比较值。
            .softValues()
            /**
             * CacheBuilder.refreshAfterWrite(long, TimeUnit)可以为缓存增加自动定时刷新功能。
             * 和expireAfterWrite相反，refreshAfterWrite通过定时刷新可以让缓存项保持可用。
             * 但请注意：缓存项只有在被检索时才会真正刷新（如果CacheLoader.refresh实现为异步，那么检索不会被刷新拖慢）。
             * 因此，如果你在缓存上同时声明expireAfterWrite和refreshAfterWrite，缓存并不会因为刷新盲目地定时重置，如果缓存项没有被检索，那刷新就不会真的发生，缓存项在过期时间后也变得可以回收。
             */
            .refreshAfterWrite(100, TimeUnit.SECONDS)

            /**
             * 声明一个监听器，以便缓存项被移除时做一些额外操作
             * 警告：默认情况下，监听器方法是在移除缓存时同步调用的。
             * 因为缓存的维护和请求响应通常是同时进行的，代价高昂的监听器方法在同步模式下会拖慢正常的缓存请求。
             * 在这种情况下，你可以使用RemovalListeners.asynchronous(RemovalListener, Executor)把监听器装饰为异步操作
             */
            .removalListener(new RemovalListener<String, String>() {
                // notification含移除原因RemovalCause、键、值。
                @Override
                public void onRemoval(RemovalNotification notification) {
                    RemovalCause cause = notification.getCause();
                    String value = (String)notification.getValue();
                    String key = (String)notification.getKey();
                    System.out.println("key:" + key + "  value:" + value + "  course:" + cause.toString());
                }
            })

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

                        @Override
                        public ListenableFuture<String> reload(String key, String oldValue) throws Exception{
                            // 刷新和回收不太一样。正如LoadingCache.refresh(K)所声明，刷新表示为键加载新值，这个过程可以是异步的。
                            // 在刷新操作进行时，缓存仍然可以向其他线程返回旧值，而不像回收操作，读缓存的线程必须等待新值加载完成。
                            // 重载CacheLoader.reload(K, V)可以扩展刷新时的行为，这个方法允许开发者在计算新值时使用旧的值。
                            return null;
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


        // 显式清除
        cache.invalidate("1");// 单个
        List<String> list = Lists.newArrayList("1", "2");
        cache.invalidateAll(list);// 批量
        cache.invalidateAll(); // 清除所有




    }




}
