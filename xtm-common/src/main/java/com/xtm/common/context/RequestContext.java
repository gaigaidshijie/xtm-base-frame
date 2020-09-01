package com.xtm.common.context;

import java.util.Map;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/6 15:34
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public interface RequestContext {
    RequestContext add(String key, String value);

    String get(String key);

    RequestContext remove(String key);

    Map<String, String> getAttributes();
}
