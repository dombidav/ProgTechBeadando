package com.hl5u4v.progtech.app.views.user;

import com.hl5u4v.progtech.core.interfaces.IShow;

public class UserAllow_View implements IShow {
    @Override
    public void show() {
        System.out.println("New \"allow\" rule added");
    }

    public void showDisallow() {
        System.out.println("New \"disallow\" rule added");
    }
}
