package com.cyd.cache.config;

import com.cyd.cache.manager.RedissonManager;
import com.cyd.cache.property.RedissonAutoConfigurationProperty;
import com.cyd.cache.utils.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedissonAutoConfigurationProperty.class)
public class RedissonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Order(value = 2)
    public RedissonLock redissonLock(RedissonManager redissonManager) {
        RedissonLock redissonLock = new RedissonLock(redissonManager);
        log.info("[RedissonLock]组装完毕");
        return redissonLock;
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(value = 1)
    public RedissonManager redissonManager(RedissonAutoConfigurationProperty redissonAutoConfigurationProperty) {
        RedissonManager redissonManager =
                new RedissonManager(redissonAutoConfigurationProperty);
        log.info("[RedissonManager]组装完毕,当前连接方式:" + redissonAutoConfigurationProperty.getType() +
                ",连接地址:" + redissonAutoConfigurationProperty.getAddress());
        return redissonManager;
    }

}
