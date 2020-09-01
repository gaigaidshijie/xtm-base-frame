package com.xtm.security.interceptor;

import com.bh.exception.CustomException;
import com.xtm.security.el.PreAuthorizeExpressionRoot;
import com.xtm.security.spec.Spec;
import com.xtm.security.util.RestfulMatchUtil;
import com.xtm.security.util.SpringElCheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 15:11
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@RequiredArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final List<Spec> specList;
    private final PreAuthorizeExpressionRoot preAuthorizeExpressionRoot;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 当前请求的路径和定义的规则能够匹配
        Boolean checkResult = specList.stream()
                .filter(spec -> RestfulMatchUtil.match(request, spec.getHttpMethod(), spec.getPath()))
                .findFirst()
                .map(spec -> {
                    String expression = spec.getExpression();
                    return SpringElCheckUtil.check(
                            new StandardEvaluationContext(preAuthorizeExpressionRoot),
                            expression
                    );

                })
                .orElse(true);
        if (!checkResult) {
            throw new CustomException(401,"没有权限");
        }

        return true;
    }
}
