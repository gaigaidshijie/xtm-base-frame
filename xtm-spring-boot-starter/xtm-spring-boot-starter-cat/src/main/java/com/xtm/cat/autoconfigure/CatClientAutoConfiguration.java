package com.xtm.cat.autoconfigure;

import com.xtm.cat.CatClientInit;
import com.xtm.cat.aspect.CatTransactionAspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/6 16:05
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
public class CatClientAutoConfiguration {
    @Value("${spring.application.name:unknown}")
    private String domain;

    @Value("${cat.servers:}")
    private String servers;

    @Bean
    public CatClientInit catClientInit() {
        return new CatClientInit(domain, servers);
    }

    @Bean
    public CatTransactionAspect catTransactionAspect() {
        return new CatTransactionAspect();
    }
}
