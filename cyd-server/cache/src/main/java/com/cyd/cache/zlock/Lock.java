package com.cyd.cache.zlock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description  : [分布式锁注解版]
 * @author       : [pengqiushi]
 * @version      : [v1.0]
 * @Copyright (C), 2018-2020
 * @createTime   : [2021/7/18 21:24]
 * @updateUser   : [pengqiushi]
 * @updateTime   : [2021/7/18 21:24]
 * @updateRemark : [说明本次修改内容]
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    /**
     * 锁的key
     */
    String key();
    /**
     * 获取锁的最大尝试时间(单位 {@code unit})
     * 该值大于0则使用 locker.tryLock 方法加锁，否则使用 locker.lock 方法
     */
    long waitTime() default 0;
    /**
     * 加锁的时间(单位 {@code unit})，超过这个时间后锁便自动解锁；
     * 如果leaseTime为-1，则保持锁定直到显式解锁
     */
    long leaseTime() default -1;
    /**
     * 参数的时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;
    /**
     * 是否公平锁
     */
    boolean isFair() default false;
}
