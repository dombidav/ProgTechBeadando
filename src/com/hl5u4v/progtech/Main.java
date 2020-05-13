package com.hl5u4v.progtech;

import com.hl5u4v.progtech.core.APP;

import java.util.Scanner;

public class Main {

    public static boolean exit = false;

    public static void main(String[] args) {
        APP.start();
        Scanner in = new Scanner(System.in);
        while (!exit) {
            var command = in.nextLine();
            APP.call(command);
        }
    }
}
