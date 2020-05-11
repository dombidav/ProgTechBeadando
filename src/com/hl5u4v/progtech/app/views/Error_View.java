package com.hl5u4v.progtech.app.views;

import com.hl5u4v.progtech.core.interfaces.IView;

public class Error_View implements IView {

    public void warn(String message) {
        System.out.println("\u001b[31m" + message + "\u001b[0m");
    }

    public void crash(String message) {
        System.out.println(message);
        System.out.println("See error log for details.");
        System.out.println("The program will exit now.");
    }
}
