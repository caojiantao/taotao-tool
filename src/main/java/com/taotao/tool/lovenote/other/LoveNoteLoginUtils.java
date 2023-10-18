package com.taotao.tool.lovenote.other;

import com.taotao.tool.lovenote.model.LoveNoteUser;

public class LoveNoteLoginUtils {

    private static ThreadLocal<LoveNoteUser> userLocal = new ThreadLocal<>();

    public static void setCurrentUser(LoveNoteUser user) {
        userLocal.set(user);
    }

    public static LoveNoteUser getCurrentUser() {
        return userLocal.get();
    }

    public static void clearCurrentUser() {
        userLocal.remove();
    }
}
