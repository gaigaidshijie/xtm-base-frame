package com.xtm.security.el;

import com.xtm.security.domain.SecurityContext;
import com.xtm.security.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/27 14:23
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
@Slf4j
@AllArgsConstructor
public class PreAuthorizeExpressionRoot {

    public boolean anon() {
        return true;
    }

    public boolean hasLogin() {
        return SecurityContext.getUser()!=null;
    }

    public boolean hasRole(String role) {
        return hasAnyRoles(role);
    }

    public boolean hasAllRoles(String... roles) {
        User user = SecurityContext.getUser();
        if (user == null) {
            return false;
        }

        List<String> userRoles = user.getRoles();
        if (CollectionUtils.isEmpty(userRoles)) {
            return false;
        }
        List<String> roleList = Arrays.asList(roles);
        return userRoles.containsAll(roleList);
    }

    public boolean hasAnyRoles(String... roles) {
        User user = SecurityContext.getUser();
        if (user == null) {
            return false;
        }

        List<String> userRoles = user.getRoles();
        List<String> roleList = Arrays.asList(roles);
        if (CollectionUtils.isEmpty(userRoles)) {
            return false;
        }

        boolean checkResult = userRoles.stream()
                .anyMatch(roleList::contains);
        if (!checkResult) {
            log.warn("角色不匹配，userRolesFromToken = {}, roles = {}", userRoles, roles);
        }
        return checkResult;
    }
}
