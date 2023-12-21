package com.taotao.tool.carpool.other;


import com.taotao.tool.carpool.model.CarpoolUser;

public class CarpoolLoginUtils {

    private static ThreadLocal<CarpoolUser> userLocal = new ThreadLocal<>();

    public static void setCurrentUser(CarpoolUser user) {
        userLocal.set(user);
    }

    public static CarpoolUser getCurrentUser() {
        return userLocal.get();
    }

    public static void clearCurrentUser() {
        userLocal.remove();
    }
}
