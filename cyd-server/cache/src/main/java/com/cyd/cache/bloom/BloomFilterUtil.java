package com.cyd.cache.bloom;

/**
 * @description  : [布隆过滤器共同类]
 * @author       : [xgx-pengqiushi]
 * @version      : [v1.0]
 * @Copyright (C), 2018-2020
 * @createTime   : [2021/7/6 14:10]
 * @updateUser   : [pengqiushi]
 * @updateTime   : [2021/7/6 14:10]
 * @updateRemark : [说明本次修改内容]  
 */
public class BloomFilterUtil {

  public static final String REDIS_PREFIX = "redis://";
  public static final String REDIS_DEFAULT_BLOOM_FILTER_NAME= "cyd_bloom_default_key:";
  public static final long EXPECTED_INSERTION = 100000000L;
  public static final double FALSE_PROBABILITY = 0.03;
  public static final long EXPIRE_DATE = 60;
  public static final String KEY_EVENT_EXPIRED = "__keyevent@0__:expired";

}
