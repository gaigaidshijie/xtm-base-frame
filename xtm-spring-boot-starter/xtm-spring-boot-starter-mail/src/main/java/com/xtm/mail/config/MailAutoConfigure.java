package com.xtm.mail.config;

import com.xtm.mail.properties.MailProperties;
import com.xtm.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/19 14:56
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
@ConditionalOnProperty(prefix = "xtm.mail",name = "enable", havingValue = "true")
public class MailAutoConfigure {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailProperties mailProperties;

    @Bean
    public MailService mailService(){
        return new MailService(mailSender,mailProperties);
    }
}
