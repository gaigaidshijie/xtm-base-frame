package com.xtm.cat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description:[Cat手动埋点注解] </p>
 * Created on : 2020/8/6 16:02
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CatTransaction {

    /**
     * 类型, 默认为Method
     * @return
     */
    String type() default "";

    /**
     * 名称, 默认为类名.方法名
     * @return
     */
    String name() default "";

    /**
     * 是否保存参数信息到Cat
     * @return
     */
    boolean isSaveParamToCat() default true;
}
