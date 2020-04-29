package com.hl5u4v.progtech.views;

import java.util.Hashtable;

public class ErrorView extends View {
    private int code;
    private String message;

    public ErrorView(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorView(int code, String message, Hashtable<String, Object> variables) {
        super(variables);
        this.code = code;
        this.message = message;
    }

    @Override
    public void show() {
        System.out.println(String.format("ERROR %s: %s\n", code, message));
        System.out.println("See error log for details.");
        System.out.println("The program will exit now.");
    }
}
