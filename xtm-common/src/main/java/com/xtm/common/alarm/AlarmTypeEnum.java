package com.xtm.common.alarm;

/**
 * <p>Description:[告警类型] </p>
 * Created on : 2020/8/6 15:28
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public enum AlarmTypeEnum {

    /**
     * 钉钉
     */
    DING_TALK("DingTalk"),
    /**
     * 外部系统
     */
    EXTERNAL_SYSTEM("ExternalSystem");

    AlarmTypeEnum(String type) {
        this.type = type;
    };

    private String type;

    public String getType() {
        return type;
    }
}
