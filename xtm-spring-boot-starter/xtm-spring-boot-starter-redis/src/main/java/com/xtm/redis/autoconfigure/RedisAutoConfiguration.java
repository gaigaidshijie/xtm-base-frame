package com.xtm.redis.autoconfigure;

import com.xtm.redis.RedisAspect;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/21 09:43
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
@AutoConfigureAfter({org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class})
@ConditionalOnBean(RedisConnectionFactory.class)
public class RedisAutoConfiguration {
    @Bean
    public RedisAspect openTracingRedisAspect() {
        return new RedisAspect();
    }
}
