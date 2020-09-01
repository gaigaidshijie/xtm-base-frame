package com.xtm.log.service;

import com.xtm.log.model.AuditLog;

/**
 * <p>Description:[日志接口] </p>
 * Created on : 2020/8/17 14:37
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public interface IAuditService {

    /**
     * 保存日志
     * @param log
     */
    void save(AuditLog log);
}
