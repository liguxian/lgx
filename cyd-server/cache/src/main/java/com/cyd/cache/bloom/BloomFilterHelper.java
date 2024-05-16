package com.cyd.cache.bloom;

import com.cyd.cache.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author : [pengqiushi]
 * @version : [v1.0]
 * @description : [布隆过滤器]
 * @Copyright (C), 2018-2020
 * @createTime : [2021/7/27 10:22]
 * @updateUser : [pengqiushi]
 * @updateTime : [2021/7/27 10:22]
 * @updateRemark : [说明本次修改内容]
 */
@Slf4j
public class BloomFilterHelper {

    private static final Redisson redisson = SpringUtils.getBean(Redisson.class);

    private String bloomFilterKey;
    private long expireDate;
    private long expectedInsertion;
    private double falseProbability;
    private TimeUnit unit;

    public RBloomFilter getBloomFilter(String bloomFilterKey) {
        buildBloomFilterAttribute(bloomFilterKey, 0, 0, 0.0, null);
        RBloomFilter<String> bloomFilter = this.redisson.getBloomFilter(this.bloomFilterKey);
        /**初始化默认布隆过滤器**/
        boolean isSuccess = bloomFilter.tryInit(this.expectedInsertion, this.falseProbability);
        if (isSuccess) {
            /**设置默认的过期时间2个月**/
            bloomFilter.expire(this.expireDate, this.unit);
        }
        return bloomFilter;
    }

    public RBloomFilter<T> getBloomFilter(String bloomFilterKey, long expireDate, long expectedInsertion, double falseProbability, TimeUnit unit) {
        buildBloomFilterAttribute(bloomFilterKey, expireDate, expectedInsertion, falseProbability, unit);
        RBloomFilter<T> bloomFilter = this.redisson.getBloomFilter(this.bloomFilterKey);
        /**初始化默认布隆过滤器**/
        boolean isSuccess = bloomFilter.tryInit(this.expectedInsertion, this.falseProbability);
        if (isSuccess) {
            /**设置默认的过期时间2个月**/
            bloomFilter.expire(this.expireDate, this.unit);
        }
        return bloomFilter;
    }

    private void buildBloomFilterAttribute(String bloomFilterKey, long expireDate,
                                           long expectedInsertion, double falseProbability, TimeUnit unit) {

        this.bloomFilterKey = BloomFilterUtil.REDIS_DEFAULT_BLOOM_FILTER_NAME;
        this.expireDate = BloomFilterUtil.EXPIRE_DATE;
        this.expectedInsertion = BloomFilterUtil.EXPECTED_INSERTION;
        this.falseProbability = BloomFilterUtil.FALSE_PROBABILITY;
        this.unit = TimeUnit.SECONDS;
        if (!StringUtils.isEmpty(bloomFilterKey)) {
            this.bloomFilterKey = bloomFilterKey;
        }
        if (expireDate > 0) {
            this.expireDate = expireDate;
        }
        if (expectedInsertion > 0) {
            this.expectedInsertion = expireDate;
        }
        if (falseProbability > 0.0) {
            this.falseProbability = falseProbability;
        }
        if (unit != null) {
            this.unit = unit;
        }
    }

    /***
     * 添加一个布隆过滤器
     * @param bloomName    布隆过滤器名字
     */
    public void addBloomFilter(String bloomName, String bloomValue, long bloomMax, double bloomOdds ) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter(bloomName);
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%
        boolean bloom = bloomFilter.tryInit(bloomMax, bloomOdds);
        if (bloom){
            //设置默认时间
            bloomFilter.expire(expireDate,TimeUnit.DAYS);
        }
        bloomFilter.add(bloomValue);
    }

    /***
     * 获取布隆过滤器存在数量
     * @param bloomName    布隆过滤器名字
     * @return
     */
    public long getBloomFilterSize(String bloomName) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter(bloomName);
        return bloomFilter.getSize();
    }

    /***
     * 获取布隆过滤器存在数量
     * @param bloomName    布隆过滤器名字
     * @return
     */
    public long getBloomFilterCount(String bloomName) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter(bloomName);
        return bloomFilter.count();
    }
    /***
     * 获取布隆过滤器是否存在
     * @param bloomName    布隆过滤器名字
     * @return
     */
    public boolean getBloomFilterContains(String bloomName) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter(bloomName);
        return bloomFilter.contains(bloomName);
    }
}
