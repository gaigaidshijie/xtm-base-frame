package com.xtm.lock.idempotent;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <p>Description:[幂等接口] </p>
 * Created on : 2020/8/24 09:21
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public interface DistributedIdempotent {

    /**
     * 幂等执行
     * @param key 幂等Key
     * @param lockExpireTime 锁的过期时间
     * @param firstLevelExpireTime 一级存储过期时间
     * @param secondLevelExpireTime 二级存储过期时间
     * @param timeUnit 存储时间单位
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    <T> T execute(String key, int lockExpireTime, int firstLevelExpireTime, int secondLevelExpireTime, TimeUnit timeUnit, Supplier<T> execute, Supplier<T> fail);

    /**
     * 幂等执行
     * @param request 幂等参数
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    <T> T execute(IdempotentRequest request, Supplier<T> execute, Supplier<T> fail);

    /**
     * 幂等执行
     * @param key 幂等Key
     * @param lockExpireTime 锁的过期时间
     * @param firstLevelExpireTime 一级存储过期时间
     * @param secondLevelExpireTime 二级存储过期时间
     * @param timeUnit 存储时间单位
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    void execute(String key, int lockExpireTime, int firstLevelExpireTime, int secondLevelExpireTime, TimeUnit timeUnit, Runnable execute, Runnable fail);

    /**
     * 幂等执行
     * @param request 幂等参数
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    void execute(IdempotentRequest request, Runnable execute, Runnable fail);
}
