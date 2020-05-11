package com.hl5u4v.progtech.core.auth;

import com.hl5u4v.progtech.app.models.User;
import org.jetbrains.annotations.Nullable;

public class Auth {
    private IAuthenticable user = null;
    private static Auth instance;

    private Auth() { }
    private Auth(IAuthenticable user){
        this.user = user;
    }

    @Nullable
    public static IAuthenticable getUser() {
        return instance == null ? null : instance.user;
    }

    public static boolean check() {
        return getUser() != null;
    }

    public static void logout() {
        instance.user = null;
    }

    public static void login(User user) {
        instance = new Auth(user);
    }


}

