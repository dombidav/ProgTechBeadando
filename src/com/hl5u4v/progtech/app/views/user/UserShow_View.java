package com.hl5u4v.progtech.app.views.user;

import com.hl5u4v.progtech.app.models.User;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IShowModel;

public class UserShow_View implements IShowModel {

    @Override
    public void show(IModel user) {
        for (var key : user.fields().keySet()) {
            System.out.printf("\t%s%s:%s\t%s%n", ANSI_YELLOW, key, ANSI_RESET, user.fields().get(key));
        }
        System.out.printf("\t%sAllowed locks: %s%n", ANSI_YELLOW, ANSI_RESET);
        for (var lock : ((User) user).getLocks()) {
            System.out.printf("\t %s|>\t%s%s\t%s%s%n", ANSI_YELLOW, ANSI_CYAN, lock.getId(), ANSI_RESET, lock.getName());
        }
    }
}
