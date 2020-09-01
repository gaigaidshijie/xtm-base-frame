package com.xtm.security.annotation.support;

import com.bh.exception.CustomException;
import com.xtm.security.annotation.PreAuthorize;
import com.xtm.security.el.PreAuthorizeExpressionRoot;
import com.xtm.security.util.SpringElCheckUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * <p>Description:[处理PreAuthorize的切面] </p>
 * Created on : 2020/8/27 15:09
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Aspect
@AllArgsConstructor
public class PreAuthorizeAspect {

    private final PreAuthorizeExpressionRoot preAuthorizeExpressionRoot;

    @Around("@annotation(com.xtm.security.annotation.PreAuthorize) ")
    public Object preAuth(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(PreAuthorize.class)) {
            PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);

            String expression = preAuthorize.value();
            boolean check = SpringElCheckUtil.check(
                    new StandardEvaluationContext(preAuthorizeExpressionRoot),
                    expression
            );
            if (!check) {
                throw new CustomException(401,"no access");
            }
        }
        return point.proceed();
    }
}
