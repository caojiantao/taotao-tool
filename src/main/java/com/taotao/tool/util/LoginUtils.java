package com.taotao.tool.util;

import com.taotao.tool.model.User;

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
