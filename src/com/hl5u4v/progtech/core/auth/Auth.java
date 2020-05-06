package com.hl5u4v.progtech.core.auth;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class Auth {
    private IAuthenticable user = null;
    private static Auth instance;

    private Auth() { }
    private Auth(IAuthenticable user){
        this.user = user;
    }

    public static void set(IAuthenticable user){
        instance = new Auth(user);
    }

    @Nullable
    public static IAuthenticable getUser(){
        return instance == null ? null : instance.user;
    }

    public static boolean check(){
        return getUser() != null;
    }
}

