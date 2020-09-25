package com.delicacy.cookies.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redisson属性装配类
 * @author feigu
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    /** 超时时长*/
    private int timeout = 3000;

    /** 地址*/
    private String address;

    /** 密码*/
    private String password;

    /** 链接线程大小*/
    private int connectionPoolSize = 64;

    /** 最小连接数*/
    private int connectionMinimumIdleSize=10;

    /** 从库连接大小*/
    private int slaveConnectionPoolSize = 250;

    /** 主库连接大小*/
    private int masterConnectionPoolSize = 250;

    /** 哨兵地址*/
    private String[] sentinelAddresses;

    /** 主服名*/
    private String masterName;

}