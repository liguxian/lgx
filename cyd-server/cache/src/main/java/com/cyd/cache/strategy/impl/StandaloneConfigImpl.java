package com.cyd.cache.strategy.impl;



import com.cyd.cache.constant.GlobalConstant;
import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import com.cyd.cache.strategy.RedissonConfigService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;

/**
 * @Description: 单机部署Redisson配置
 * pqs
 */
@Slf4j
public class StandaloneConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
        Config config = new Config();
        try {
            String address = redissonAutoConfigurationProperty.getAddress();
            String password = redissonAutoConfigurationProperty.getPassword();
            int port = redissonAutoConfigurationProperty.getPort();
            int database = redissonAutoConfigurationProperty.getDatabase();
            String redisAddr = GlobalConstant.REDIS_CONNECTION_PREFIX.getConstantValue() + address;
            config.useSingleServer().setAddress(redisAddr.concat(":").concat(port+""));
            config.useSingleServer().setDatabase(database);
            //密码可以为空
            if (StringUtils.isNotBlank(password)) {
                config.useSingleServer().setPassword(password);
            }
            log.info("初始化[单机部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("单机部署 Redisson init error", e);
        }
        return config;
    }
}
