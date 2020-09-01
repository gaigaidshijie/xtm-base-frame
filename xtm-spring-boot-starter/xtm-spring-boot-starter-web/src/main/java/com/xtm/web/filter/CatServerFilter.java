package com.xtm.web.filter;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xtm.common.cat.CatConstantsExt;
import com.xtm.common.cat.CatContext;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>Description:[Cat 监控过滤器] </p>
 * Created on : 2020/8/6 16:43
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class CatServerFilter implements Filter {

    @Value("${spring.application.name:unknown}")
    private String applicationName;

    public CatServerFilter(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        // 构建远程消息树
        if(request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID) != null){
            CatContext catContext = new CatContext();
            catContext.addProperty(Cat.Context.ROOT,request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID));
            catContext.addProperty(Cat.Context.PARENT,request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID));
            catContext.addProperty(Cat.Context.CHILD,request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID));
            Cat.logRemoteCallServer(catContext);
        }

        Transaction filterTransaction = Cat.newTransaction(CatConstants.TYPE_URL, request.getMethod() + ":"+uri);

        try {
            Cat.logEvent(CatConstantsExt.TYPE_URL_METHOD, request.getMethod(), Message.SUCCESS, request.getRequestURL().toString());
            Cat.logEvent(CatConstantsExt.TYPE_URL_CLIENT, request.getRemoteHost() + "【" + applicationName + "】");

            filterChain.doFilter(servletRequest, servletResponse);
            filterTransaction.setSuccessStatus();
        } catch (Exception e) {
            filterTransaction.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            filterTransaction.complete();
        }
    }

    @Override
    public void destroy() {

    }
}
