package com.xtm.common.cat;

import com.dianping.cat.CatConstants;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/6 15:30
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class CatConstantsExt extends CatConstants {

    public static final String CAT_HTTP_HEADER_CHILD_MESSAGE_ID = "X-CAT-CHILD-ID";
    public static final String CAT_HTTP_HEADER_PARENT_MESSAGE_ID = "X-CAT-PARENT-ID";
    public static final String CAT_HTTP_HEADER_ROOT_MESSAGE_ID = "X-CAT-ROOT-ID";
    public static final String TYPE_URL_METHOD = "URL.Method";
    public static final String METHOD = "Method";
    public static final String TYPE_URL_CLIENT = "URL.Client";
    public static final String TYPE_CALL_FEIGN = "HttpCall.Feign";
    public static final String TYPE_CALL_RESTTEMPLATE = "HttpCall.RestTemplate";
}
