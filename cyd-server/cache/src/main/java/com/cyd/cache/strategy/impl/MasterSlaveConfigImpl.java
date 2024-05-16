package com.cyd.cache.strategy.impl;


import com.cyd.cache.constant.GlobalConstant;
import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import com.cyd.cache.strategy.RedissonConfigService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:  主从部署Redisson配置
 *       连接方式:  主节点,子节点,子节点
 *         格式为:  127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
 */

@Slf4j
public class MasterSlaveConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
        Config config = new Config();
        try {
            String address = redissonAutoConfigurationProperty.getAddress();
            String password = redissonAutoConfigurationProperty.getPassword();
            int database = redissonAutoConfigurationProperty.getDatabase();
            String[] addrTokens = address.split(",");
            String masterNodeAddr = addrTokens[0];
            //设置主节点ip
            config.useMasterSlaveServers().setMasterAddress(masterNodeAddr);
            if (StringUtils.isNotBlank(password)) {
                config.useMasterSlaveServers().setPassword(password);
            }
            config.useMasterSlaveServers().setDatabase(database);
            //设置从节点，移除第一个节点，默认第一个为主节点
            List<String> slaveList = new ArrayList<>();
            for (String addrToken : addrTokens) {
                slaveList.add(GlobalConstant.REDIS_CONNECTION_PREFIX.getConstantValue() + addrToken);
            }
            slaveList.remove(0);

            config.useMasterSlaveServers().addSlaveAddress((String[]) slaveList.toArray());
            log.info("初始化[主从部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("主从部署 Redisson init error", e);
            e.printStackTrace();
        }
        return config;
    }

}
