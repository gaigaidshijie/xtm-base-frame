package com.xtm.servicecall.feign;

import com.dianping.cat.Cat;
import com.xtm.common.cat.CatConstantsExt;
import com.xtm.common.cat.CatContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>Description:[Feign拦截器，Cat消息树生成] </p>
 * Created on : 2020/9/1 10:11
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Value("${spring.application.name:unknown}")
    private String applicationName;

    @Override
    public void apply(RequestTemplate template) {
        CatContext catContext = new CatContext();
        Cat.logRemoteCallClient(catContext,Cat.getManager().getDomain());
        template.header(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, catContext.getProperty(Cat.Context.ROOT));
        template.header(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, catContext.getProperty(Cat.Context.PARENT));
        template.header(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, catContext.getProperty(Cat.Context.CHILD));
        template.header("service-name", applicationName);
    }
}
