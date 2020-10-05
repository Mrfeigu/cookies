package com.delicacy.cookies.redisson.distribute.utils;

import org.redisson.api.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88
 *
 * redisson 实现的队列
 * 该对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
 *
 * @author linzhenghui
 * @date 2020/9/29
 */

@Component
public class RedisQueueDemoService {

    @Resource
    private RedissonClient redisson;

    /**
     * 这个相当于堆
     * @return
     */
    public Object demo(){
        RQueue<String> queue = redisson.getQueue("anyQueue");
        queue.add("真");
        queue.add("不");
        queue.add("戳");
        String peek = queue.peek();
        String poll = queue.poll();
        return "demo";
    }

    /**
     * 双端队列
     * @return
     */
    public Object dequeDemo(){

        RDeque<String> queue = redisson.getDeque("anyDeque");
        queue.addFirst("针");
        queue.addLast("不");
        queue.addLast("戳");
        String s = queue.removeFirst();
        String s1 = queue.removeLast();

        return "dequeDemo";
    }

    /**
     * 阻塞队列（Blocking Queue）
     * poll, pollFromAny, pollLastAndOfferFirstTo和take方法内部采用话题订阅发布实现，在Redis节点故障转移（主从切换）或断线重连以后，内置的相关话题监听器将自动完成话题的重新订阅。
     *
     * @return
     */
    public Object blockingQueueDemo() throws InterruptedException {

        RBlockingQueue<String> queue = redisson.getBlockingQueue("anyQueue");
        queue.offer("针");
        queue.add("不");
        queue.add("戳");
        queue.add("啊");

        // take是会发生阻塞
        String take = queue.take();
        String peek = queue.peek();
        String poll = queue.poll();
        String poll1 = queue.poll(10, TimeUnit.MINUTES);

        return "blockingQueueDemo";
    }

    /**
     * 有界阻塞队列（Bounded Blocking Queue）
     * @return
     */
    public Object boundedBlockingQueueDemo() throws InterruptedException {

        RBoundedBlockingQueue<String> queue = redisson.getBoundedBlockingQueue("anyQueue");
        // 如果初始容量（边界）设定成功则返回`真（true）`，
        // 如果初始容量（边界）已近存在则返回`假（false）`。
        queue.trySetCapacity(2);

        queue.offer("针");
        queue.offer("不");
        queue.offer("戳");
        // 此时容量已满，下面代码将会被阻塞，直到有空闲为止。
        queue.put("啊");

        String peek = queue.peek();
        String poll = queue.poll();
        String poll1 = queue.poll(10, TimeUnit.MINUTES);

        return "boundedBlockingQueue";
    }

    /**
     * 阻塞双端队列（Blocking Deque）
     * @return
     * @throws InterruptedException
     */
    public Object blockingDequeDemo() throws InterruptedException {

        RBlockingDeque<Integer> deque = redisson.getBlockingDeque("anyDeque");
        deque.putFirst(1);
        deque.putLast(2);
        Integer firstValue = deque.takeFirst();
        Integer lastValue = deque.takeLast();
        Integer firstValueFirst = deque.pollFirst(10, TimeUnit.MINUTES);
        Integer lastValueLast = deque.pollLast(3, TimeUnit.MINUTES);

        return "blockingDequeDemo";
    }

    /**
     * 延迟队列（Delayed Queue）延迟发送
     *
     * 基于Redis的Redisson分布式延迟队列（Delayed Queue）结构的RDelayedQueue
     * Java对象在实现了RQueue接口的基础上提供了向队列按要求延迟添加项目的功能。该功能可以用来实现消息传送延迟按几何增长或几何衰减的发送策略。
     *
     * @return
     */
    public Object delayedQueueDemo(){

        RQueue<String> queue = redisson.getQueue("delayDemo");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(queue);
        // 10秒钟以后将消息发送到指定队列
        delayedQueue.offer("msg1", 10, TimeUnit.SECONDS);
        // 一分钟以后将消息发送到指定队列
        delayedQueue.offer("msg2", 1, TimeUnit.MINUTES);

        return "delayedQueueDemo";
    }

    /**
     * 优先队列（Priority Queue）
     * 基于Redis的Redisson分布式优先队列（Priority Queue）Java对象实现了java.util.Queue的接口。可以通过比较器（Comparator）接口来对元素排序。
     * @return
     */
    public Object priorityQequeDemo(){
        RPriorityQueue<Integer> queue = redisson.getPriorityQueue("anyQueue");
        queue.trySetComparator(new SetDemoService.MyComparator()); // 指定对象比较器
        queue.add(3);
        queue.add(1);
        queue.add(2);

        queue.removeAsync(0);
        queue.addAsync(5);

        queue.poll();
        return "priorityQequeDemo";
    }

    /**
     * 优先双端队列（Priority Deque）
     * 基于Redis的分布式无界优先阻塞双端队列（Priority Blocking Deque）Java对象实现了java.util.Deque的接口。
     * addLast、 addFirst、push方法不能再这个对里使用。PriorityBlockingDeque的最大容量是4 294 967 295个元素。
     * @return
     */
    public Object priorityDequeDemo() throws InterruptedException {
        RPriorityBlockingDeque<Integer> queue = redisson.getPriorityBlockingDeque("anyQueue");
        queue.trySetComparator(new SetDemoService.MyComparator()); // 指定对象比较器
        queue.add(2);

        queue.removeAsync(0);
        queue.addAsync(5);

        queue.pollFirst();
        queue.pollLast();
        queue.takeFirst();
        queue.takeLast();

        return "priorityDequeDemo";
    }

    /**
     * 优先阻塞队列（Priority Blocking Queue）
     * 基于Redis的分布式无界优先阻塞队列（Priority Blocking Queue）Java对象的结构与java.util.concurrent.PriorityBlockingQueue类似。
     * 可以通过比较器（Comparator）接口来对元素排序。PriorityBlockingQueue的最大容量是4 294 967 295个元素。
     * @return
     * @throws InterruptedException
     */
    public Object priorityBlockingQueueDemo() throws InterruptedException {

        RPriorityBlockingQueue<Integer> queue = redisson.getPriorityBlockingQueue("anyQueue");
        queue.trySetComparator(new SetDemoService.MyComparator()); // 指定对象比较器
        queue.add(3);
        queue.add(1);
        queue.add(2);

        queue.removeAsync(0);
        queue.addAsync(5);

        queue.take();

        return "priorityBlockingQueueDemo";
    }

    /**
     * 优先阻塞双端队列（Priority Blocking Deque）
     * @return
     * @throws InterruptedException
     */
    public Object priorityBlockingDequeDemo() throws InterruptedException {
        RPriorityBlockingDeque<Integer> queue = redisson.getPriorityBlockingDeque("anyQueue");
        queue.trySetComparator(new SetDemoService.MyComparator()); // 指定对象比较器
        queue.add(2);

        queue.removeAsync(0);
        queue.addAsync(5);

        queue.pollFirst();
        queue.pollLast();
        queue.takeFirst();
        queue.takeLast();

        return "priorityBlockingDequeDemo";
    }





}
