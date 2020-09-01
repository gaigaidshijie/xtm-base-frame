package com.xtm.lock.idempotent;

import java.lang.reflect.Method;

/**
 * <p>Description:[https://github.com/alibaba/Sentinel/tree/master/sentinel-extension/sentinel-annotation-aspectj] </p>
 * Created on : 2020/8/24 09:22
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class MethodWrapper {

    private final Method method;
    private final boolean present;

    private MethodWrapper(Method method, boolean present) {
        this.method = method;
        this.present = present;
    }

    static MethodWrapper wrap(Method method) {
        if (method == null) {
            return none();
        }
        return new MethodWrapper(method, true);
    }

    static MethodWrapper none() {
        return new MethodWrapper(null, false);
    }

    Method getMethod() {
        return method;
    }

    boolean isPresent() {
        return present;
    }
}
