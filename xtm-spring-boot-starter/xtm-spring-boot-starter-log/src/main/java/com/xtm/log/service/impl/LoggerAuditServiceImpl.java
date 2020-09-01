package com.xtm.log.service.impl;

import com.xtm.log.model.AuditLog;
import com.xtm.log.service.IAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * <p>Description:[日志实现类-打印日志] </p>
 * Created on : 2020/8/17 14:38
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Slf4j
@ConditionalOnProperty(name = "xtm.audit-log.log-type", havingValue = "logger", matchIfMissing = true)
public class LoggerAuditServiceImpl implements IAuditService {

    private static final String MSG_PATTERN = "{}|{}|{}|{}|{}|{}";

    /**
     * 格式为：{时间}|{类名}|{方法名}|{用户id}|{用户名}|{操作信息}
     * 例子：2020-02-04 09:13:34.650|user-center|com.central.user.controller.SysUserController|saveOrUpdate|1|admin|webApp|新增用户:admin
     */
    @Override
    public void save(AuditLog audit) {
        log.debug(MSG_PATTERN
                , audit.getCreateTime()
                ,  audit.getClassName(), audit.getMethodName()
                , audit.getUserId(), audit.getUserName()
                , audit.getOperation());
    }
}
