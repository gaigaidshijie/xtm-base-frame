package com.xtm.security.spec;

import com.xtm.security.enums.HttpMethod;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 15:05
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Data
public class SpecRegistry {

    private List<Spec> specList = new ArrayList<>();

    public SpecRegistry add(HttpMethod httpMethod, String path, String expression) {
        specList.add(new Spec(httpMethod, path, expression));
        return this;
    }
}
