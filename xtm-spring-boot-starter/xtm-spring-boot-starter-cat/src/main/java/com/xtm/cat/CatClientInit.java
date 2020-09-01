package com.xtm.cat;

import com.dianping.cat.Cat;
import org.springframework.util.StringUtils;

/**
 * <p>Description:[Cat Client初始化] </p>
 * Created on : 2020/8/6 16:05
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class CatClientInit {
    public CatClientInit(String domain, String servers) {
        if (StringUtils.hasText(domain) && StringUtils.hasText(servers)) {
            Cat.initializeByDomain(domain, servers);
        } else if (StringUtils.hasText(domain)) {
            Cat.initializeByDomain(domain);
        } else {
            Cat.initialize();
        }
    }
}
