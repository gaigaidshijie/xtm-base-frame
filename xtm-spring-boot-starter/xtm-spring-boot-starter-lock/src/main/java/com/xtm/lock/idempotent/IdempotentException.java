package com.xtm.lock.idempotent;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/24 09:23
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class IdempotentException extends RuntimeException {
    public IdempotentException(String message) {
        super(message);
    }
}
