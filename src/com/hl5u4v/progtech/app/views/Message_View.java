package com.hl5u4v.progtech.app.views;

import com.hl5u4v.progtech.core.interfaces.IView;

public class Message_View implements IView {
    public void success(String message) {
        System.out.printf("%s%s%s%n", ANSI_GREEN, message, ANSI_RESET);
    }

    public void fail(String message) {
        System.out.printf("%s%s%s%n", ANSI_RED, message, ANSI_RESET);
    }
}
