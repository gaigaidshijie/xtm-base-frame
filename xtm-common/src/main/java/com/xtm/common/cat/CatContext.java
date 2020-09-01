package com.xtm.common.cat;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description:[Cat上下文] </p>
 * Created on : 2020/8/6 15:31
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class CatContext implements Cat.Context {

    private Map<String, String> properties = new HashMap<>();

    @Override
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
