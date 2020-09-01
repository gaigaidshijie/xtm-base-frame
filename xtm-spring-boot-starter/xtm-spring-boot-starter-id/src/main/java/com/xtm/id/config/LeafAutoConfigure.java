package com.xtm.id.config;

import com.xtm.id.properties.LeafProperties;
import com.xtm.id.service.SnowflakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/18 16:17
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
@EnableConfigurationProperties(LeafProperties.class)
@ConditionalOnProperty(prefix = "xtm.leaf.snowflake",name = "enable", havingValue = "true")
public class LeafAutoConfigure {

    @Autowired
    private LeafProperties leafProperties;

    @Bean
    public SnowflakeService snowflakeService(){
        return new SnowflakeService(leafProperties);
    }
}
