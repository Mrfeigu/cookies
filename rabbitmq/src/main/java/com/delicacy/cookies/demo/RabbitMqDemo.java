package com.delicacy.cookies.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linzhenghui
 * @date 2020/8/25
 */
@Slf4j
@Service
public class RabbitMqDemo {


    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * amqp的生产者只关注交换机exchange,routingKey
     * 生产一条消息
     */
    public void mqProduct(String msg){
        rabbitTemplate.convertAndSend("live-monitor-report-message", "report_reduce", msg);
    }

    /**
     * amqp的消费者只关注队列的消费情况
     * @param msg
     */
    @RabbitListener(queues = "monitor_report_reduce_queue")
    public void mqConsumer(String msg){
        System.out.println(msg);
        log.info("UsingMQ#mqConsumer msg:{}", msg);
    }


}
