package com.hl5u4v.progtech.app.views.lock;

import com.hl5u4v.progtech.core.interfaces.IShow;

public class LockDelete_View implements IShow {
    @Override
    public void show() {
        System.out.printf("%sRecord deleted successfully%s%n", ANSI_GREEN, ANSI_RESET);
    }
}
