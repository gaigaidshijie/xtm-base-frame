package com.xtm.mail.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/19 15:10
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@ConfigurationProperties(prefix = "xtm.mail")
@Data
public class MailProperties {
    private boolean enable =false;
    private String username;
    private String alias;
}
