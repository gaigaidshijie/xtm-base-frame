package com.xtm.web.autoconfigure;

import com.xtm.web.filter.CatServerFilter;
import com.xtm.web.filter.IdempotentParamFilter;
import com.xtm.web.interceptor.RestTemplateRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:[Web自动配置] </p>
 * Created on : 2020/8/6 16:49
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
public class WebAutoConfiguration {

    /**
     * 幂等ID使用：Cookie中标识用户信息的名称, 有值获取对应的，无值获取所有Cookie信息
     */
    @Value("${kitty.web.userCookieName:}")
    private String userCookieName;

    /**
     * 幂等ID使用：Header中标识用户信息的名称, 有值获取对应的，无值获取所有Header信息
     */
    @Value("${kitty.web.userHeaderName:}")
    private String userHeaderName;

    @Autowired(required = false)
    private List<RestTemplate> restTemplates;

    @Value("${spring.application.name:unknown}")
    private String applicationName;

    /**
     * 配置Cat Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean catFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CatServerFilter filter = new CatServerFilter(applicationName);
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 配置幂等参数 Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean idempotentParamtFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        IdempotentParamFilter filter = new IdempotentParamFilter(userCookieName, userHeaderName);
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("idempotent-filter");
        registration.setOrder(1);
        return registration;
    }

    /**
     *  RestTemplate 拦截器
     * @return
     */
    @Bean
    public RestTemplateRequestInterceptor restTemplateRequestInterceptor() {
        RestTemplateRequestInterceptor restTemplateRequestInterceptor = new RestTemplateRequestInterceptor();
        if (restTemplates != null) {
            restTemplates.stream().forEach(restTemplate -> {
                List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                list.add(restTemplateRequestInterceptor);
                restTemplate.setInterceptors(list);
            });
        }

        return restTemplateRequestInterceptor;
    }

}
