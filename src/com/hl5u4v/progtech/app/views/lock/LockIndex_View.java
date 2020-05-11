package com.hl5u4v.progtech.app.views.lock;

import com.hl5u4v.progtech.app.models.Lock;
import com.hl5u4v.progtech.core.helpers.List2;
import com.hl5u4v.progtech.core.interfaces.IView;

public class LockIndex_View implements IView {

    public void show(List2<Lock> locks) {
        System.out.println("Locks in database:");
        for (var lock : locks) {
            System.out.printf("\t%s%s%s\t%s%n", ANSI_BLUE, lock.getId(), ANSI_RESET, lock.getName());
        }
    }
}
