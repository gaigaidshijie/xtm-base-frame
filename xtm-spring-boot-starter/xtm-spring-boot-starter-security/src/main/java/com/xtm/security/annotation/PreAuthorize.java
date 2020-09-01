package com.xtm.security.annotation;

import java.lang.annotation.*;

/**
 * <p>Description:[用于认证、鉴权的注解] </p>
 * Created on : 2020/8/27 15:08
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuthorize {
    /**
     * 待验证的Spring-EL表达式
     * 参考：https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/core.html#expressions
     *
     * @return 表达式
     * @see com.xtm.security.el.PreAuthorizeExpressionRoot
     */
    String value();
}
