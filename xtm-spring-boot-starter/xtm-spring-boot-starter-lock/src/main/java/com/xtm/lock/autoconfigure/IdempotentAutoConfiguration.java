package com.xtm.lock.autoconfigure;

import com.xtm.lock.DistributedLock;
import com.xtm.lock.idempotent.DistributedIdempotent;
import com.xtm.lock.idempotent.DistributedIdempotentAspect;
import com.xtm.lock.idempotent.DistributedIdempotentImpl;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/24 09:30
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
@AutoConfigureAfter(DistributedLockAutoConfiguration.class)
public class IdempotentAutoConfiguration {

    @Autowired
    private DistributedIdempotent distributedIdempotent;

    @Bean
    public DistributedIdempotent distributedIdempotent(RedissonClient redissonClient, DistributedLock distributedLock) {
        return new DistributedIdempotentImpl(redissonClient, distributedLock);
    }

    @Bean
    public DistributedIdempotentAspect distributedIdempotentAspect() {
        return new DistributedIdempotentAspect(distributedIdempotent);
    }
}
