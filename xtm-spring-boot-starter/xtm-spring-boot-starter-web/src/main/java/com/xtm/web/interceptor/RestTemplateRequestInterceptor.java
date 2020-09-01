package com.xtm.web.interceptor;

import com.xtm.common.cat.CatConstantsExt;
import com.xtm.common.cat.CatTransactionManager;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description:[RestTemplate 拦截器] </p>
 * Created on : 2020/8/6 16:47
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class RestTemplateRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("URI",  request.getMethodValue() + request.getURI().toString());
        data.put("Params",  new String(body));

        String name = request.getURI().getHost() + request.getURI().getPath();
        return CatTransactionManager.newTransaction(() -> {
            try {
                return execution.execute(request, body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, CatConstantsExt.TYPE_CALL_RESTTEMPLATE, name);
    }

}
