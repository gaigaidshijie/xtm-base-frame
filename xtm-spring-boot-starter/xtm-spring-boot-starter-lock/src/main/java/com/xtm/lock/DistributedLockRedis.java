package com.xtm.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.WriteRedisConnectionException;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <p>Description:[Redis 分布式锁] </p>
 * Created on : 2020/8/24 09:17
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Slf4j
public class DistributedLockRedis implements DistributedLock{

    private RedissonClient redissonClient;

    private DistributedLockMysql distributedLockMysql;

    public  DistributedLockRedis(RedissonClient redissonClient, DistributedLockMysql distributedLockMysql) {
        this.redissonClient = redissonClient;
        this.distributedLockMysql = distributedLockMysql;
    }

    @Override
    public <T> T lock(String key, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> fail) {
        return doLock(key, waitTime, leaseTime, TimeUnit.MILLISECONDS, success, fail);
    }

    @Override
    public <T> T lock(String key, int leaseTime, Supplier<T> success, Supplier<T> fail) {
        return doLock(key, 0, leaseTime, TimeUnit.MILLISECONDS, success, fail);
    }

    @Override
    public <T> T lock(String key, int leaseTime, TimeUnit timeUnit, Supplier<T> success, Supplier<T> fail) {
        return doLock(key, 0, leaseTime, timeUnit, success, fail);
    }

    @Override
    public void lock(String key, int waitTime, int leaseTime, Runnable success, Runnable fail) {
        doLock(key, waitTime, leaseTime, TimeUnit.MILLISECONDS, success, fail);
    }

    @Override
    public void lock(String key, int leaseTime, Runnable success, Runnable fail) {
        doLock(key, 0, leaseTime, TimeUnit.MILLISECONDS, success, fail);
    }

    @Override
    public void lock(String key, int leaseTime, TimeUnit timeUnit, Runnable success, Runnable fail) {
        doLock(key, 0, leaseTime, timeUnit, success, fail);
    }

    private <T> T doLock(String key, int waitTime, int leaseTime, TimeUnit timeUnit, Supplier<T> success, Supplier<T> fail) {
        try {
            RLock lock = null;
            try {
                lock = redissonClient.getLock(key);
            } catch (Exception e) {
                log.error("get Redis Lock Error", e);
                // 降级为数据库锁
                if (distributedLockMysql != null) {
                    return distributedLockMysql.lock(key, waitTime, leaseTime, success, fail);
                }
                return fail.get();
            }

            boolean tryLock = false;
            try {
                tryLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            } catch (WriteRedisConnectionException e) {
                // 写入失败，降级为数据库锁
                if (distributedLockMysql != null) {
                    return distributedLockMysql.lock(key, waitTime, leaseTime, success, fail);
                }
            }

            if (!tryLock) {
                return fail.get();
            }

            try {
                return success.get();
            } catch (Exception e){
                throw e;
            } finally {
                if (lock.getHoldCount() != 0) {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void doLock(String key, int waitTime, int leaseTime, TimeUnit timeUnit, Runnable success, Runnable fail) {
        try {
            RLock lock = null;
            try {
                lock = redissonClient.getLock(key);
            } catch (Exception e) {
                log.error("get Redis Lock Error", e);
                // 降级为数据库锁
                if (distributedLockMysql != null) {
                    distributedLockMysql.lock(key, waitTime, leaseTime, success, fail);
                }
                return;
            }

            boolean tryLock = false;
            try {
                tryLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            } catch (WriteRedisConnectionException e) {
                // 写入失败，降级为数据库锁
                if (distributedLockMysql != null) {
                    distributedLockMysql.lock(key, waitTime, leaseTime, success, fail);
                    return;
                }
            }

            if (!tryLock) {
                fail.run();
                return;
            }

            try {
                success.run();
            } catch (Exception e){
                throw e;
            } finally {
                if (lock.getHoldCount() != 0) {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
