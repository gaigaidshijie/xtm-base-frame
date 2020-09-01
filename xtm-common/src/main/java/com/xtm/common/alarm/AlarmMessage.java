package com.xtm.common.alarm;

import lombok.Builder;
import lombok.Data;

/**
 * <p>Description:[告警参数] </p>
 * Created on : 2020/8/6 15:27
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Data
@Builder
public class AlarmMessage {

    /**
     * 告警名称，区分唯一性，方便控制告警时间间隔
     */
    private String alarmName;

    /**
     * 告警类型
     */
    private AlarmTypeEnum alarmType;

    /**
     * 告警消息
     */
    private String message;

    /**
     * 钉钉机器人access_token
     */
    private String accessToken;

    /**
     * 钉钉机器人secret
     */
    private String secret;

    /**
     * 对接外部API地址
     */
    private String apiUrl;

    /**
     * 告警时间间隔，单位分钟
     */
    private int alarmTimeInterval = 1;
}
