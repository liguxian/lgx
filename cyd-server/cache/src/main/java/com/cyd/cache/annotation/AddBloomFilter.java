package com.cyd.cache.annotation;

import java.lang.annotation.*;

/**
 * @Description: 基于注解的分布式式锁
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddBloomFilter {

    /**
     * 布隆过滤器名字
     */
    String key() default "jdy_bloom";
    /**
     * 布隆过滤器的值
     */
    String value() default "jdy_bloom";

    /**
     * 布隆过滤器有效时间
     */
    int leaseTime() default 2626560;


    /**
     * 布隆过滤器最大数量
     */
    long max() default  10000000L;

    /**
     * 布隆过滤器错误率， 默认为百分之三
     */
    double odds() default  0.3;


}


