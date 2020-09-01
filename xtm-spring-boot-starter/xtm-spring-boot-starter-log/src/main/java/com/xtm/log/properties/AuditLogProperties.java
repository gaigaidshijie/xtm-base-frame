package com.xtm.log.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * <p>Description:[日志配置] </p>
 * Created on : 2020/8/17 14:10
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "xtm.audit-log")
@RefreshScope
public class AuditLogProperties {

    /**
     * 是否开启审计日志
     */
    private Boolean enabled = false;
    /**
     * 日志记录类型(logger/redis/db/es)
     */
    private String logType;
}
