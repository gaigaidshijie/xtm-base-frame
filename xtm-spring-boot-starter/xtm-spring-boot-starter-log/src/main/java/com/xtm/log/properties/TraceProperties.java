package com.xtm.log.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * <p>Description:[日志链路追踪配置] </p>
 * Created on : 2020/8/17 14:12
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "xtm.trace")
@RefreshScope
public class TraceProperties {

    /**
     * 是否开启日志链路追踪
     */
    private Boolean enable = false;
}
