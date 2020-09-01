package com.xtm.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 14:24
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String id;
    private String username;
    private List<String> roles;
}
