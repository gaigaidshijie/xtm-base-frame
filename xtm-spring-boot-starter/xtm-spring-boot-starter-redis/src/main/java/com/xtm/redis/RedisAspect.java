package com.xtm.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/21 09:37
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Aspect
public class RedisAspect {

    @Pointcut("target(org.springframework.data.redis.connection.RedisConnectionFactory)")
    public void connectionFactory() {}

    @Pointcut("execution(org.springframework.data.redis.connection.RedisConnection *.getConnection(..))")
    public void getConnection() {}

    @Pointcut("execution(org.springframework.data.redis.connection.RedisClusterConnection *.getClusterConnection(..))")
    public void getClusterConnection() {}

    @Around("getConnection() && connectionFactory()")
    public Object aroundGetConnection(final ProceedingJoinPoint pjp) throws Throwable {
        RedisConnection connection = (RedisConnection) pjp.proceed();
        return new CatMonitorRedisConnection(connection);
    }

    @Around("getClusterConnection() && connectionFactory()")
    public Object aroundGetClusterConnection(final ProceedingJoinPoint pjp) throws Throwable {
        RedisClusterConnection clusterConnection = (RedisClusterConnection) pjp.proceed();
        return new CatMonitorRedisClusterConnection(clusterConnection);
    }
}
