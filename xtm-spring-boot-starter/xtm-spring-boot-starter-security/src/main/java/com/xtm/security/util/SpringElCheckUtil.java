package com.xtm.security.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 15:04
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@UtilityClass
@Slf4j
public class SpringElCheckUtil {
    private static ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 校验expression是否能通过rootObject的检测
     *
     * @param context    上下文
     * @param expression 表达式
     * @return 是否通过
     */
    public static boolean check(EvaluationContext context, String expression) {
        Boolean result = PARSER.parseExpression(expression)
                .getValue(context, Boolean.class);
        log.info("expression = {}, eval result = {}", expression, result);
        return result != null ? result : false;
    }
}
