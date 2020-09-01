package com.xtm.log.properties;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description:[日志数据源配置,logType=db时生效(非必须)，如果不配置则使用当前数据源] </p>
 * Created on : 2020/8/17 14:11
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "xtm.audit-log.datasource")
public class LogDbProperties extends HikariConfig {
}
