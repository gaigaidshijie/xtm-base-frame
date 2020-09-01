package com.xtm.servicecall.dubbo.constants;

/**
 * <p>Description:[代码来自官方给出的示列：https://github.com/dianping/cat/tree/master/integration/dubbo] </p>
 * Created on : 2020/9/1 10:05
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class CatConstants {
    public static final String CROSS_CONSUMER ="RpcCall";

    public static final String CROSS_SERVER = "RpcService";

    public static final String PROVIDER_APPLICATION_NAME="serverApplicationName";

    public static final String CONSUMER_CALL_SERVER="RpcCall.server";

    public static final String CONSUMER_CALL_APP="RpcCall.app";

    public static final String CONSUMER_CALL_PORT="RpcCall.port";

    public static final String PROVIDER_CALL_SERVER="RpcService.client";

    public static final String PROVIDER_CALL_APP="RpcService.app";
}
