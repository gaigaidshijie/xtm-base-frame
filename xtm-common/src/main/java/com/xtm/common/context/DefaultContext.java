package com.xtm.common.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/6 15:34
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class DefaultContext implements RequestContext {

    private final Map<String, String> attributes = new HashMap<String, String>();

    @Override
    public RequestContext add(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public String get(String key) {
        return attributes.get(key);
    }

    @Override
    public RequestContext remove(String key) {
        attributes.remove(key);
        return this;
    }

    @Override
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}
