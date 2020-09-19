package com.delicacy.cookies.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 队列参数配置
 * @author linzhenghui
 * @date 2020/9/11
 */
@Slf4j
// @Component
public class QueueConfig {

    /**
     * 队列交换器
     */
    public static final String ORANGE_QUEUE_KEY = "orange_queue_key";
    /**
     * 死信队列路由
     */
    public static final String DEAD_ORANGE_ROUTE = "dead_orange_route";

    /**
     * 队列路由
     */
    public static final String ORANGE_ROUTE = "orange_route";

    /**
     * 队列名
     */
    public static final String QUEUE_NAME = "orange_queue";

    /**
     * 原队列
     * @return
     */
    @Bean(name = "orangeQueue")
    Queue originQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", ORANGE_QUEUE_KEY)
                .withArgument("x-dead-letter-routing-key", DEAD_ORANGE_ROUTE)
                .build();
    }

    /**
     * 死信队列
     * @return
     */
    @Bean(name = "deadOrangeQueue")
    Queue deadQueue(){
        return new Queue("dead_orange_queue");
    }

    /**
     * 交换
     * @return
     */
    @Bean(name = "orangeExchange")
    public CustomExchange orangeExchange() {
        return new CustomExchange(ORANGE_QUEUE_KEY, "direct", true, false);
    }

    /**
     * 死性队列绑定
     * @param deadQueue
     * @param orangeExchange
     * @return
     */
    @Bean
    public Binding deadQueueBinding(@Qualifier("deadOrangeQueue") Queue deadQueue, @Qualifier("orangeExchange") CustomExchange orangeExchange) {
        return BindingBuilder.bind(deadQueue).to(orangeExchange).with(DEAD_ORANGE_ROUTE).noargs();
    }

    /**
     * 普通队列绑定
     * @param orangeQueue
     * @param orangeExchange
     * @return
     */
    @Bean
    public Binding queueBinding(@Qualifier("orangeQueue") Queue orangeQueue, @Qualifier("orangeExchange") CustomExchange orangeExchange) {
        return BindingBuilder.bind(orangeQueue).to(orangeExchange).with(ORANGE_ROUTE).noargs();
    }


}
