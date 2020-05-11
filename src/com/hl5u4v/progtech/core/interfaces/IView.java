package com.hl5u4v.progtech.core.interfaces;

import java.util.Scanner;

public interface IView {
    String ANSI_RESET = "\u001B[0m";
    String ANSI_BLACK = "\u001B[30m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_WHITE = "\u001B[37m";

    default String ask(String question, String pattern) {
        Scanner in = new Scanner(System.in);
        System.out.printf("\n%s%s%s", ANSI_YELLOW, question, ANSI_CYAN);
        var input = in.nextLine().trim();
        while (!input.matches(pattern)) {
            System.out.printf("%sInput does not match the pattern /%s/%n", ANSI_RED, pattern);
            input = in.nextLine().trim();
        }
        System.out.print(ANSI_RESET);
        return input;
    }

    default String ask(String question) {
        Scanner in = new Scanner(System.in);
        System.out.printf("\n%s%s%s", ANSI_YELLOW, question, ANSI_CYAN);
        var input = in.nextLine().trim();
        while (input.length() < 1) {
            System.out.printf("%sInput must contain at least one character%n", ANSI_RED);
            input = in.nextLine().trim();
        }
        System.out.print(ANSI_RESET);
        return input;
    }
}
