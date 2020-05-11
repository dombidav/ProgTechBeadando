package com.hl5u4v.progtech.app.views.user;

import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IView;

public class UserIndex_View implements IView {

    public void show(List2<User> users) {
        System.out.println("Users in database:");
        for (var user : users) {
            System.out.printf("\t%s%s%s\t%s\t%s%n", ANSI_BLUE, user.getId(), ANSI_RESET, user.getName(), user.getEmail());
        }
    }
}
