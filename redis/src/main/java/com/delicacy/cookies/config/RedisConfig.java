package com.delicacy.cookies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author linzhenghui
 * @date 2020/9/16
 */
//@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory getRedisConnectionFactory(){
        // todo RedisConnectionFactory这个Bean的继承是哪一个呢

        return null;
    }

    


}
