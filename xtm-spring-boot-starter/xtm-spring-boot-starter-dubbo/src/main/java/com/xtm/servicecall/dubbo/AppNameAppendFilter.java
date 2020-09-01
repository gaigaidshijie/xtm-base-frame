package com.xtm.servicecall.dubbo;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * <p>Description:[代码来自官方给出的示列：https://github.com/dianping/cat/tree/master/integration/dubbo] </p>
 * Created on : 2020/9/1 10:06
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Activate(group = {CommonConstants.CONSUMER})
public class AppNameAppendFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().setAttachment(CommonConstants.APPLICATION_KEY,invoker.getUrl().getParameter(CommonConstants.APPLICATION_KEY));
        return invoker.invoke(invocation);
    }
}
