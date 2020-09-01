package com.xtm.cat.aspect;

import com.xtm.cat.annotation.CatTransaction;
import com.xtm.common.cat.CatConstantsExt;
import com.xtm.common.cat.CatTransactionManager;
import com.xtm.common.json.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description:[atTransaction 切面] </p>
 * Created on : 2020/8/6 16:04
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Aspect
public class CatTransactionAspect {

    @Around("@annotation(catTransaction)")
    public Object aroundAdvice(ProceedingJoinPoint joinpoint, CatTransaction catTransaction) throws Throwable {
        String type = catTransaction.type();
        if (StringUtils.isEmpty(type)){
            type = CatConstantsExt.METHOD;
        }
        String name = catTransaction.name();
        if (StringUtils.isEmpty(name)){
            name = joinpoint.getSignature().getDeclaringType().getSimpleName() + "." + joinpoint.getSignature().getName();
        }

        Map<String, Object> data = new HashMap<>(1);
        if (catTransaction.isSaveParamToCat()) {
            Object[] args = joinpoint.getArgs();
            if (args != null) {
                data.put("params", JsonUtils.toJson(args));
            }
        }

        return CatTransactionManager.newTransaction(() -> {
            try {
                return joinpoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }, type, name, data);
    }
}
