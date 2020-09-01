package com.xtm.lock.autoconfigure;

import com.xtm.lock.DistributedLock;
import com.xtm.lock.DistributedLockMysql;
import com.xtm.lock.DistributedLockRedis;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * <p>Description:[分布式锁自动配置] </p>
 * Created on : 2020/8/24 09:29
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
public class DistributedLockAutoConfiguration {

    @Autowired(required = false)
    private DataSource dataSource;

    @Bean("distributedLockMysql")
    public DistributedLock distributedLockMysql() {
        return new DistributedLockMysql(dataSource);
    }

    @Bean("distributedLockRedis")
    @Primary
    @DependsOn("distributedLockMysql")
    public DistributedLock distributedLockRedis(RedissonClient redissonClient, DistributedLockMysql distributedLockMysql) {
        return new DistributedLockRedis(redissonClient, distributedLockMysql);
    }

}
