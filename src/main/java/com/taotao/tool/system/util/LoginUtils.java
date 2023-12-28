package com.taotao.tool.system.util;

import com.taotao.tool.system.model.User;

public class LoginUtils {

    private static ThreadLocal<User> userLocal = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        userLocal.set(user);
    }

    public static User getCurrentUser() {
        return userLocal.get();
    }

    public static void clearCurrentUser() {
        userLocal.remove();
    }
}
