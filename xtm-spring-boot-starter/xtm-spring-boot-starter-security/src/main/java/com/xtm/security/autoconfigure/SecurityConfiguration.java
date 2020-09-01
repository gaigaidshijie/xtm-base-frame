package com.xtm.security.autoconfigure;

import com.xtm.security.annotation.support.PreAuthorizeAspect;
import com.xtm.security.el.PreAuthorizeExpressionRoot;
import com.xtm.security.spec.Spec;
import com.xtm.security.spec.SpecRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>Description:[配置类] </p>
 * Created on : 2020/8/27 15:17
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Configuration
@AutoConfigureBefore(SecurityAutoConfiguration.class)
public class SecurityConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PreAuthorizeExpressionRoot preAuthorizeExpressionRoot() {
        return new PreAuthorizeExpressionRoot();
    }

    @Bean
    @ConditionalOnMissingBean
    public PreAuthorizeAspect preAuthorizeAspect(PreAuthorizeExpressionRoot preAuthorizeExpressionRoot) {
        return new PreAuthorizeAspect(preAuthorizeExpressionRoot);
    }

    @Bean
    @ConditionalOnBean(SpecRegistry.class)
    public List<Spec> specListFromSpecRegistry(SpecRegistry specRegistry, SecurityProperties lightSecurityProperties) {
        List<Spec> specList = specRegistry.getSpecList();
        if (CollectionUtils.isEmpty(specList)) {
            throw new IllegalArgumentException("specList cannot be empty.");
        }
        return this.specListFromProperties(lightSecurityProperties);
    }

    @Bean
    public List<Spec> specListFromProperties(SecurityProperties lightSecurityProperties) {
        return lightSecurityProperties.getSpecList();
    }
}
