package com.cyd.cache.manager;

import autovalue.shaded.com.google.common.common.base.Preconditions;
import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import com.cyd.cache.strategy.RedissonConfigService;
import com.cyd.cache.strategy.impl.ClusterConfigImpl;
import com.cyd.cache.strategy.impl.MasterSlaveConfigImpl;
import com.cyd.cache.strategy.impl.SentinelConfigImpl;
import com.cyd.cache.strategy.impl.StandaloneConfigImpl;
import com.cyd.cache.type.RedisConnectionType;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;


/**
 *  Redisson核心配置，用于提供初始化的redisson实例
 *
 */
@Slf4j
public class RedissonManager {


    private Config config = new Config();

    private Redisson redisson = null;

    public RedissonManager() {
    }

    public RedissonManager(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
        try {
            //通过不同部署方式获得不同config实体
             config = RedissonConfigFactory.getInstance().createConfig(redissonAutoConfigurationProperty);
            redisson = (Redisson) Redisson.create(config);
        } catch (Exception e) {
            log.error("Redisson init error", e);
            throw new IllegalArgumentException("please input correct configurations," +
                    "connectionType must in standalone/sentinel/cluster/masterslave");
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }


        /**
         * 根据连接类型获取对应连接方式的配置,基于策略模式
         *
         * @param redissonAutoConfigurationProperty redis连接信息
         * @return Config
         */
        Config createConfig(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
            Preconditions.checkNotNull(redissonAutoConfigurationProperty);
            Preconditions.checkNotNull(redissonAutoConfigurationProperty.getAddress(), "redisson.server.address cannot be NULL!");
            Preconditions.checkNotNull(redissonAutoConfigurationProperty.getType(), "redisson.server.password cannot be NULL");
            Preconditions.checkNotNull(redissonAutoConfigurationProperty.getDatabase(), "redisson.server.database cannot be NULL");
            String connectionType = redissonAutoConfigurationProperty.getType();
            //声明配置上下文
            RedissonConfigService redissonConfigService = null;
            if (connectionType.equals(RedisConnectionType.STANDALONE.getConnectionType())) {
                redissonConfigService = new StandaloneConfigImpl();
            } else if (connectionType.equals(RedisConnectionType.SENTINEL.getConnectionType())) {
                redissonConfigService = new SentinelConfigImpl();
            } else if (connectionType.equals(RedisConnectionType.CLUSTER.getConnectionType())) {
                redissonConfigService = new ClusterConfigImpl();
            } else if (connectionType.equals(RedisConnectionType.MASTER_SLAVE.getConnectionType())) {
                redissonConfigService = new MasterSlaveConfigImpl();
            } else {
                throw new IllegalArgumentException("创建Redisson连接Config失败！当前连接方式:" + connectionType);
            }
            return redissonConfigService.createRedissonConfig(redissonAutoConfigurationProperty);
        }
    }

}


