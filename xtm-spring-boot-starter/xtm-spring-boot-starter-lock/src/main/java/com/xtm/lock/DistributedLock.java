package com.xtm.lock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <p>Description:[分布式锁接口] </p>
 * Created on : 2020/8/24 09:14
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */

public interface DistributedLock {

    /**
     * 加锁
     * @param key 锁Key
     * @param waitTime 尝试加锁，等待时间 (ms)
     * @param leaseTime 上锁后的失效时间 (ms)
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return
     */
    <T> T lock(String key, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> fail);

    /**
     * 加锁, 加锁失败立即返回
     * @param key 锁Key
     * @param leaseTime 上锁后的失效时间 (ms)
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return
     */
    <T> T lock(String key, int leaseTime, Supplier<T> success, Supplier<T> fail);

    /**
     * 加锁, 加锁失败立即返回
     * @param key 锁Key
     * @param leaseTime 上锁后的失效时间
     * @param timeUnit 时间单位
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return
     */
    <T> T lock(String key, int leaseTime, TimeUnit timeUnit, Supplier<T> success, Supplier<T> fail);

    /**
     * 加锁
     * @param key 锁Key
     * @param waitTime 尝试加锁，等待时间 (ms)
     * @param leaseTime 上锁后的失效时间 (ms)
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return
     */
    void lock(String key, int waitTime, int leaseTime, Runnable success, Runnable fail);

    /**
     * 加锁, 加锁失败立即返回
     * @param key 锁Key
     * @param leaseTime 上锁后的失效时间 (ms)
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return
     */
    void lock(String key, int leaseTime, Runnable success, Runnable fail);

    /**
     * 加锁, 加锁失败立即返回
     * @param key 锁Key
     * @param leaseTime 上锁后的失效时间
     * @param timeUnit 时间单位
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return
     */
    void lock(String key, int leaseTime, TimeUnit timeUnit, Runnable success, Runnable fail);
}
