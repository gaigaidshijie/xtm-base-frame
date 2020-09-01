package com.xtm.security.autoconfigure;

import com.xtm.security.spec.Spec;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:[配置属性] </p>
 * Created on : 2020/8/27 15:14
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@ConfigurationProperties(prefix = "xtm-security")
@Getter
@Setter
public class SecurityProperties {

    private List<Spec> specList = new ArrayList<>();
}
