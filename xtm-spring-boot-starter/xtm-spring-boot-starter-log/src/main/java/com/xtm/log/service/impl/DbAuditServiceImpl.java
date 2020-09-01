package com.xtm.log.service.impl;

import com.dianping.cat.util.StringUtils;
import com.xtm.log.model.AuditLog;
import com.xtm.log.properties.LogDbProperties;
import com.xtm.log.service.IAuditService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/17 14:42
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */

@Slf4j
@ConditionalOnProperty(name = "xtm.audit-log.log-type", havingValue = "db")
@ConditionalOnClass(JdbcTemplate.class)
public class DbAuditServiceImpl implements IAuditService {

    private static final String INSERT_SQL = " insert into sys_logger " +
            " (`id`, `class_name`, `method_name`, `params`, `user_id`, `user_name`, `operation`, `create_time`) " +
            " values (?,?,?,?,?,?,?,?)";

    private final JdbcTemplate jdbcTemplate;

    public DbAuditServiceImpl(@Autowired(required = false) LogDbProperties logDbProperties, DataSource dataSource) {
        //优先使用配置的日志数据源，否则使用默认的数据源
        if (logDbProperties != null && StringUtils.isNotEmpty(logDbProperties.getJdbcUrl())) {
            dataSource = new HikariDataSource(logDbProperties);
        }
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS `sys_logger` (\n" +
                "  `id` varchar(50) NOT NULL,\n" +
                "  `class_name` varchar(255) DEFAULT NULL COMMENT '类名',\n" +
                "  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名',\n" +
                "  `params` varchar(255) DEFAULT NULL COMMENT '参数',\n" +
                "  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',\n" +
                "  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',\n" +
                "  `operation` varchar(255) DEFAULT NULL COMMENT '操作信息',\n" +
                "  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';";
        this.jdbcTemplate.execute(sql);
    }

    @Async
    @Override
    public void save(AuditLog audit) {
        this.jdbcTemplate.update(INSERT_SQL
                , audit.getId(), audit.getClassName(), audit.getMethodName(), audit.getParams()
                , audit.getUserId(), audit.getUserName()
                , audit.getOperation(), audit.getCreateTime());
    }
}
