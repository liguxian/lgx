package com.cyd.cache.strategy;


import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import org.redisson.config.Config;

/**
 * @Description: Redisson配置构建接口\
 * pqs
 *
 */
public interface RedissonConfigService {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     * @param redissonAutoConfigurationProperty
     * @return Config
     */
    Config createRedissonConfig(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty);
}
