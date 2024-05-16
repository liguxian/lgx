package com.cyd.cache.strategy.impl;



import com.cyd.cache.constant.GlobalConstant;
import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import com.cyd.cache.strategy.RedissonConfigService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;


/**
 * @Description: 哨兵集群部署Redis连接配置
 *
 */
@Slf4j
public class SentinelConfigImpl implements RedissonConfigService {



    @Override
    public Config createRedissonConfig(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
        Config config = new Config();
        try {
            String address = redissonAutoConfigurationProperty.getAddress();
            String password = redissonAutoConfigurationProperty.getPassword();
            int database = redissonAutoConfigurationProperty.getDatabase();
            String[] addrTokens = address.split(",");
            String sentinelAliasName = addrTokens[0];
            //设置redis配置文件sentinel.conf配置的sentinel别名
            config.useSentinelServers().setMasterName(sentinelAliasName);
            config.useSentinelServers().setDatabase(database);
            if (StringUtils.isNotBlank(password)) {
                config.useSentinelServers().setPassword(password);
            }
            //设置sentinel节点的服务IP和端口
            for (int i = 1; i < addrTokens.length; i++) {
                config.useSentinelServers().addSentinelAddress(GlobalConstant.REDIS_CONNECTION_PREFIX.getConstantValue() + addrTokens[i]);
            }
            log.info("初始化[哨兵部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("哨兵部署 Redisson init error", e);

        }
        return config;
    }
}
