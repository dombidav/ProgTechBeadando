package com.hl5u4v.progtech.app.views.lock;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.core.interfaces.IModel;
import com.hl5u4v.progtech.core.interfaces.IShowModel;

public class LockShow_View implements IShowModel {

    @Override
    public void show(IModel lock) {
        for (var key : lock.fields().keySet()) {
            System.out.printf("\t%s%s:%s\t%s%n", ANSI_YELLOW, key, ANSI_RESET, lock.fields().get(key));
        }
        System.out.printf("\t%sAllowed users: %s%n", ANSI_YELLOW, ANSI_RESET);
        for (var user : ((Lock) lock).getUsers()) {
            System.out.printf("\t %s|>\t%s%s\t%s%s%n", ANSI_YELLOW, ANSI_CYAN, user.getId(), ANSI_RESET, user.getName());
        }
    }
}
