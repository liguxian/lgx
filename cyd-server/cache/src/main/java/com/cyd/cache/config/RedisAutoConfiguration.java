package com.cyd.cache.config;

import com.cyd.cache.annotation.LockAspect;
import com.cyd.cache.property.RedisAutoConfigurationProperty;
import com.cyd.cache.utils.SpringUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


/**
 * @author pqs
 * @ClassName: RedisConfig
 * @Description: 配置redis、配置LettuceConnectionFactory、
 * <p>springboot2.1.3默认是LettuceConnectionFactory.非jedis</p>
 * @date 2021年7月26日 下午7:24:11
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisAutoConfigurationProperty.class)
@Import({

        SpringUtils.class,
//        LockTemplate.class,
        SpringUtils.class,
        LockAspect.class
})
public class RedisAutoConfiguration extends CachingConfigurerSupport {

    private LettuceConnectionFactory lettuceConnectionFactory;

    @Autowired
    private RedisAutoConfigurationProperty redisAutoConfigurationProperty;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public KeyGenerator wiselyKeyGenerator() {

        return (o, method, params) -> {
            StringBuilder sb = new StringBuilder();
            //、类目
            sb.append(o.getClass().getName());
            //、方法名
            sb.append(method.getName());
            for (Object param : params) {
                sb.append(param.toString());
            }
            return sb.toString();
        };
    }

    /**
     * <p>redisTemplate</p>
     * <p>Description:设置 RedisTemplate</p>
     * <p>Company: pqs </p>
     *
     * @param lettuceConnectionFactory
     * @return
     * @author pqs
     * @version 1.0
     */
    @Bean
    @Primary
    @ConditionalOnClass(RedisTemplate.class)
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * <p>lettuceConnectionFactory</p>
     * <p>Description: LettuceConnectionFactory配置操作redis</p>
     * <p>Company: jdyun </p>
     *
     * @return
     * @author jdyun-ceo
     * @version 1.0
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisAutoConfigurationProperty.getHost());
        redisStandaloneConfiguration.setPort(redisAutoConfigurationProperty.getPort());
        redisStandaloneConfiguration.setDatabase(redisAutoConfigurationProperty.getDatabase());
        redisStandaloneConfiguration.setPassword(redisAutoConfigurationProperty.getPassword());
        LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder = LettuceClientConfiguration.builder();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfigurationBuilder.build());
        this.lettuceConnectionFactory = factory;
        return factory;
    }

    /**
     * <p>cacheManager</p>
     * <p>Description:设置redis CacheManager</p>
     * <p>Company: pqs </p>
     *
     * @return
     * @author pqs
     * @version 1.0
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        RedisCacheConfiguration redisCacheUserConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))//key序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))//value序列化方式
                .entryTtl(Duration.ofSeconds(60));
        RedisCacheManagerBuilder.fromCacheWriter(redisCacheWriter);
        RedisCacheManagerBuilder builder = RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory())
                .cacheDefaults(redisCacheUserConfiguration)
                .transactionAware();
        return builder.build();
    }

    /**
     * <p>keySerializer</p>
     * <p>Description:K序列化方式 </p>
     * <p>Company: pqs </p>
     *
     * @return
     * @author pqs
     * @version 1.0
     */
    protected RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * <p>valueSerializer</p>
     * <p>Description: value序列化方式</p>
     * <p>Company: pqs </p>
     *
     * @return
     * @author pqs
     * @version 1.0
     */
    protected RedisSerializer<Object> valueSerializer() {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
