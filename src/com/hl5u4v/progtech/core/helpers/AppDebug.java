package com.hl5u4v.progtech.core.helpers;

public class AppDebug {
    public static void dd(Object rs) {
        System.out.println(rs);
        System.exit(0);
    }

    public static void log(String line) {
        System.out.println(line);
    }
}
