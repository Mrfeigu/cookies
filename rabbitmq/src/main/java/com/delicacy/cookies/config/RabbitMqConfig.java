package com.delicacy.cookies.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType.NONE;


/**
 * @author linzhenghui
 * @date 2020/9/11
 */

@Slf4j
// @Configuration
public class RabbitMqConfig {

    @Primary
    @Bean(name = "rabbitMqConnectionFactory")
    public ConnectionFactory getRabbitMqConnectionFactory(
            @Value("${spring.rabbitmq.host}") String host,
            @Value("${spring.rabbitmq.port}") Integer port,
            @Value("${spring.rabbitmq.username}") String username,
            @Value("${spring.rabbitmq.password}") String password,
            @Value("${spring.rabbitmq.publisher-returns}") Boolean publisherReturns){

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost("/");
        // 缓存模式
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        // 缓存大小
        cachingConnectionFactory.setChannelCacheSize(4);
        //当大于0时，启用通道限制
        cachingConnectionFactory.setChannelCheckoutTimeout(0);
        // 消息的可靠性投递
        cachingConnectionFactory.setPublisherConfirmType(NONE);
        // 消息可靠确认
        cachingConnectionFactory.setPublisherReturns(publisherReturns);
        return cachingConnectionFactory;
    }

    @Primary
    @Bean(name = "rabbitTemplate")
    public RabbitTemplate getRabbitTemplate(@Qualifier("rabbitMqConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置序列化方式
        // rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        //确认推到mq服务器
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause)
                -> log.info("confirm callback id:{},ack:{},cause:{}", correlationData, ack, cause));
        //确认推到指定的队列
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey)
                -> log.info("return callback message：{},code:{},text:{}", message, replyCode, replyText));
        return rabbitTemplate;
    }

}
