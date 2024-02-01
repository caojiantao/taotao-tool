package com.taotao.tool.system.util;

import com.taotao.tool.system.model.SystemUser;

public class LoginUtils {

    private static final ThreadLocal<SystemUser> userLocal = new ThreadLocal<>();

    public static void setCurrentUser(SystemUser user) {
        userLocal.set(user);
    }

    public static SystemUser getCurrentUser() {
        return userLocal.get();
    }

    public static void clearCurrentUser() {
        userLocal.remove();
    }
}
