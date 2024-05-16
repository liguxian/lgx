package com.cyd.cache.zlock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description  : [锁对象的抽象]
 * @author       : [pengqiushi]
 * @version      : [v1.0]
 * @Copyright (C), 2018-2020,
 * @createTime   : [2021/7/18 21:24]
 * @updateUser   : [pengqiushi]
 * @updateTime   : [2021/7/18 21:24]
 * @updateRemark : [说明本次修改内容]
 */
@AllArgsConstructor
public class ZLock implements AutoCloseable {
    @Getter
    private final Object lock;

    private final LuckLockTemplate locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}
