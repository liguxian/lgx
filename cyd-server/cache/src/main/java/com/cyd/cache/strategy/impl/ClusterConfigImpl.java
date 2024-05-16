package com.cyd.cache.strategy.impl;



import com.cyd.cache.constant.GlobalConstant;
import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import com.cyd.cache.strategy.RedissonConfigService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;

/**
 * @Description: 集群方式Redisson部署
 *      地址格式：
 *          cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
 *          格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
 */
@Slf4j
public class ClusterConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
        Config config = new Config();
        try {
            String address = redissonAutoConfigurationProperty.getAddress();
            String password = redissonAutoConfigurationProperty.getPassword();
            String[] addrTokens = address.split(",");
            //设置cluster节点的服务IP和端口
            for (int i = 0; i < addrTokens.length; i++) {
                config.useClusterServers()
                        .addNodeAddress(GlobalConstant.REDIS_CONNECTION_PREFIX.getConstantValue() + addrTokens[i]);
                if (StringUtils.isNotBlank(password)) {
                    config.useClusterServers().setPassword(password);
                }
            }
            log.info("初始化[集群部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("集群部署 Redisson init error", e);
            e.printStackTrace();
        }
        return config;
    }
}
