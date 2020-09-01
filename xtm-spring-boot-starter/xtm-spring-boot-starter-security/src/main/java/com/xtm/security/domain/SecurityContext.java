package com.xtm.security.domain;

import com.bh.exception.CustomException;
import com.xtm.security.constants.ConstantsSecurity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 14:46
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public final class SecurityContext {

    private static final ConcurrentHashMap<String,User> ONLINE_USERS = new ConcurrentHashMap<>();

    public static void addUser(String token,User user){
        ONLINE_USERS.put(token,user);
    }

    public static User getUser (String token){
        return ONLINE_USERS.get(token);
    }

    public static void removeUser(String token){
        ONLINE_USERS.remove(token);
    }

    public static User getUser (){
        HttpServletRequest request = getRequest();
        String token = getTokenFromRequest(request);
        User user = ONLINE_USERS.get(token);
        if(user==null){
            throw new CustomException(401,"没有获取到用户信息");
        }
        request.setAttribute("x-userid-header",user.getId());
        request.setAttribute("x-user-header",user.getUsername());
        return user;
    }


    private static String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(ConstantsSecurity.AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(header)) {
            throw new CustomException(401,"没有找到名为token的header");
        }

        return header;
    }

    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if ((requestAttributes == null)) {
            throw new CustomException(401,"requestAttributes为null");
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
