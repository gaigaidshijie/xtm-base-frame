package com.xtm.security.spec;

import com.xtm.security.enums.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 15:05
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spec {

    /**
     * 请求方法
     */
    private HttpMethod httpMethod;
    /**
     * 路径
     */
    private String path;
    /**
     * 表达式
     * - hasLogin() 判断是否登录
     * - permitAll() 直接允许访问
     * - hasRole('角色名称') 判断是否具备指定角色
     * - hasAnyRole('角色1','角色2','角色3') 是否具备角色1/2/3中的任意一个角色
     * -
     */
    private String expression;
}
