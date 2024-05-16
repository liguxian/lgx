package com.cyd.cache.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 全局常量枚举 用来拼接完整的URL
 */
@Getter
@AllArgsConstructor
public enum GlobalConstant {

    REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

    private  String constantValue;
    private  String constantDesc;

}
