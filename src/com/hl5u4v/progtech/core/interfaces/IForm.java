package com.hl5u4v.progtech.core.interfaces;

import java.util.Scanner;

public interface IForm extends IView {
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
