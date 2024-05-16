package com.cyd.cache.property;

import com.cyd.cache.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = CommonConstants.REDISSON_CONFIG_PREFIX)
@Configuration
public class RedissonAutoConfigurationProperty {

    /**
     * redis主机地址，ip：port，有多个用半角逗号分隔
     */
    private String address;

    /**
     * 连接类型，支持standalone-单机节点，sentinel-哨兵，cluster-集群，masterSlave-主从
     */
    private String type;

    /**
     * redis 连接密码
     */
    private String password;

    /**
     * 选取那个数据库
     */
    private int database;

    private int port;

    public RedissonAutoConfigurationProperty setPassword(String password) {
        this.password = password;
        return this;
    }

    public RedissonAutoConfigurationProperty setDatabase(int database) {
        this.database = database;
        return this;
    }
}
