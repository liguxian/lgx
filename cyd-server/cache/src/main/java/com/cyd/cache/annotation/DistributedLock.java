package com.cyd.cache.annotation;

import java.lang.annotation.*;

/**
 * @Description: 基于注解的分布式式锁
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {

    /**
     * 锁的名称
     */
    String value() default "jcgl_lock";

    /**
     * 锁的有效时间
     */
    int leaseTime() default 10;
}


