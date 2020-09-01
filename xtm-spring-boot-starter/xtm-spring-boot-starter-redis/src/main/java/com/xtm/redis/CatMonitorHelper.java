package com.xtm.redis;

import com.xtm.common.cat.CatTransactionManager;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/21 09:34
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class CatMonitorHelper {

    private int maxKeysLength = 10;

    private final String REDIS = "Redis";

    public void execute(String command, Runnable runnable) {
        Map<String, Object> data = new HashMap<>(1);
        CatTransactionManager.newTransaction(runnable, REDIS, command, data);
    }

    public void execute(String command, byte[] key, Runnable runnable) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("key", deserialize(key));
        CatTransactionManager.newTransaction(runnable, REDIS, command, data);
    }

    public void execute(String command, byte[][] keys, Runnable runnable) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("keys", toStringWithDeserialization(limitKeys(keys)));
        CatTransactionManager.newTransaction(runnable, REDIS, command, data);
    }

    public <T> T execute(String command, byte[] key, Supplier<T> supplier) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("key", deserialize(key));
        return CatTransactionManager.newTransaction(supplier, REDIS, command, data);
    }

    public <T> T execute(String command, Supplier<T> supplier) {
        return CatTransactionManager.newTransaction(supplier, REDIS, command);
    }

    public <T> T execute(String command, byte[][] keys, Supplier<T> supplier) {
        Map<String, Object> data = new HashMap<>(1);
        data.put("keys", toStringWithDeserialization(limitKeys(keys)));
        return CatTransactionManager.newTransaction(supplier, REDIS, command, data);
    }

    private static String deserialize(byte[] bytes) {
        return (bytes == null ? "" : new String(bytes, StandardCharsets.UTF_8));
    }

    private static String toStringWithDeserialization(byte[][] array) {
        if (array == null) {
            return "";
        }

        List<String> list = new ArrayList<>();
        for (byte[] bytes : array) {
            list.add(deserialize(bytes));
        }

        return "[" + String.join(", ", list) + "]";
    }

    <T> T[] limitKeys(T[] keys) {
        if (keys != null && keys.length > maxKeysLength) {
            return Arrays.copyOfRange(keys, 0, maxKeysLength);
        }
        return keys;
    }
}
