package com.xtm.common.context;


/**
 * <p>Description:[] </p>
 * Created on : 2020/8/6 15:33
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class ContextHolder {

    private static final ThreadLocal<RequestContext> contextHolder = new ThreadLocal<RequestContext>() {
        @Override
        protected RequestContext initialValue() {
            return new DefaultContext();
        }
    };


    public static RequestContext getCurrentContext() {
        return contextHolder.get();
    }

    public static void setCurrentContext(RequestContext requestContext) {
        contextHolder.set(requestContext);
    }


    public static void clearCurrentContext() {
        contextHolder.remove();
    }
}
