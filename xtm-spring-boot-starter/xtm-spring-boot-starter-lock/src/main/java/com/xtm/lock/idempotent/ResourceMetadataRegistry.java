package com.xtm.lock.idempotent;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/24 09:24
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public final class ResourceMetadataRegistry {

    private static final Map<String, MethodWrapper> HANDLER_MAP = new ConcurrentHashMap<>();

    static MethodWrapper lookupHandler(Class<?> clazz, String name) {
        return HANDLER_MAP.get(getKey(clazz, name));
    }

    static void updateHandlerFor(Class<?> clazz, String name, Method method) {
        if (clazz == null || !StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Bad argument");
        }
        HANDLER_MAP.put(getKey(clazz, name), MethodWrapper.wrap(method));
    }

    private static String getKey(Class<?> clazz, String name) {
        return String.format("%s:%s", clazz.getCanonicalName(), name);
    }
}
