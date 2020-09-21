package com.delicacy.cookies.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author linzhenghui
 * @date 2020/9/16
 */
@Configuration
public class RedisConfig {

    @Bean
    // @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> buildRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置工厂
        redisTemplate.setConnectionFactory(factory);
        // value 设置序列化
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(new DefaultBaseTypeLimitingValidator());
        serializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(serializer);
        // key 设置序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        // hash 的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringSerializer);
        // hash 的value设置序列化
        redisTemplate.setHashValueSerializer(serializer);
        //
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
