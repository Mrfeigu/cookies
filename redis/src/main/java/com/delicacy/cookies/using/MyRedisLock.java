package com.delicacy.cookies.using;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * 1. 保证某时刻只有一个客户端持有锁
 * 2. 有超时机制，确保不会死锁
 * 3. 释放锁时不会释放到其他客户端的锁
 *
 * @author linzhenghui
 * @date 2020/10/10
 */
@Slf4j
@Component
public class MyRedisLock {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password:#{null}}")
    private String password;

    /** key存在，set就会失败*/
    private static final String SET_IF_NOT_EXIST = "NX";
    /** 单位是秒*/
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    /** 单位是毫秒*/
    private static final String SET_WITH_EXPIRE_TIME_PX = "PX";
    /** 成功获取锁*/
    private static final String LOCK_SUCCESS = "OK";
    /** 成功释放锁*/
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取锁
     * @param key 锁定的key
     * @param uniqueId 替换的value，一般建议用uuid，用于确定是哪个客户端获取锁
     * @param seconds 持有锁时长
     * @return true or false
     */
    public boolean tryLock(String key, String uniqueId, int seconds) {
        boolean lock = false;
        try (Jedis jedis = getJedis()) {
            if (LOCK_SUCCESS.equals(jedis.set(key, uniqueId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds))) {
                lock = true;
            }
        } catch (Exception ex) {
            log.error("RedisLock error. key:{}, value:{}", key, uniqueId, ex);
        }
        return lock;
    }

    /**
     * 释放锁
     * @param key 待释放的key
     * @param uniqueId 替换的value，一般建议用uuid，用于确定是哪个客户端获取锁
     * @return true or false
     */
    public boolean releaseLock(String key, String uniqueId){
        boolean unlock = false;
        try (Jedis jedis = getJedis()) {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            if (RELEASE_SUCCESS.equals(jedis.eval(script, Collections.singletonList(key), Collections.singletonList(uniqueId)))) {
                unlock = true;
            }
        } catch (Exception ex) {
            log.error("RedisUnLock error. key:{}, value:{}", key, uniqueId, ex);
        }
        return unlock;
    }

    /**
     * 获取jedis
     * @return
     */
    public Jedis getJedis() {
        //Redis客户端
        Jedis jedis = new Jedis(host, port);
        // 线上的密码是有配置的
        if (password != null && !password.isEmpty()) {
            jedis.auth(password);
        }
        return jedis;
    }


}
