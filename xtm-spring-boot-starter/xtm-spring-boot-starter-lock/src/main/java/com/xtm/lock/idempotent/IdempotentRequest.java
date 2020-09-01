package com.xtm.lock.idempotent;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/24 09:22
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Data
@Builder
public class IdempotentRequest {

    /**
     * 幂等Key
     */
    private String key;

    /**
     * 一级存储过期时间
     */
    private int firstLevelExpireTime;

    /**
     * 二级存储过期时间
     */
    private int secondLevelExpireTime;

    /**
     * 锁的过期时间
     */
    private int lockExpireTime;

    /**
     * 存储时间单位
     */
    private TimeUnit timeUnit;
}
