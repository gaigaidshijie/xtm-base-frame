package com.xtm.log.model;

import lombok.Data;

/**
 * <p>Description:[日志] </p>
 * Created on : 2020/8/17 14:18
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Data
public class AuditLog {

    private String id;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 操作信息
     */
    private String operation;
    /**
     * 参数
     */
    private String params;

    /**
     * 创建时间
     */
    private String createTime;
}
