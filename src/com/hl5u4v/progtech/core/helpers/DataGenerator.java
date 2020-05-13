package com.hl5u4v.progtech.core.helpers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class DataGenerator {

    @NotNull
    @Contract(value = " -> new", pure = true)
    public static DataGenerator get() {
        return new DataGenerator();
    }

    public String name() {
        return String.format("%s %s", firstName(), lastName());
    }

    public String email() {
        Random rnd = new Random();
        var first = firstName().toLowerCase();
        var last = lastName().toLowerCase();
        var middle = rnd.nextBoolean() ? "_" : ".";
        if (rnd.nextBoolean()) {
            var temp = first;
            first = last;
            last = temp;
        }
        if (rnd.nextBoolean()) {
            last += rnd.nextInt(1000);
            if (rnd.nextBoolean()) {
                first += rnd.nextInt(1000);
            } else if (rnd.nextBoolean()) {
                middle = String.valueOf(rnd.nextInt(1000));
            }
        }
        return String.format("%s%s%s@example.com", rnd.nextBoolean() ? first : first.substring(0, 1), rnd.nextBoolean() ? last : last.substring(0, 2), middle);
    }

    /**
     * Create data from the pattern: (escape with '\')
     * # = random digit
     * % = random digit or nothing
     * $ = random letter or digit
     * * = random letter or digit or nothing
     * + = random letter
     * ? = random letter or nothing
     *
     * @param pattern pattern with # ; % ; $ ; * ; + ; ?
     * @return random string based on the pattern.
     */
    public String fromPattern(@NotNull String pattern) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        var escape = false;
        for (char c : pattern.toCharArray()) {
            rnd.nextInt();
            if (escape) {
                result.append(c);
                escape = false;
            } else {
                switch (c) {
                    case '\\':
                        escape = true;
                        break;
                    case '#':
                        result.append(rnd.nextInt(10));
                        break;
                    case '%':
                        var num = rnd.nextInt(11);
                        result.append(num < 10 ? num : "");
                        break;
                    case '*':
                        if (rnd.nextBoolean()) {
                            break;
                        }
                    case '$':
                        result.append(charFrom("abcdefghijklmnopqrstuvwxyz0123456789"));
                        break;
                    case '?':
                        if (!rnd.nextBoolean()) {
                            break;
                        }
                    case '+':
                        result.append(charFrom("abcdefghijklmnopqrstuvwxyz"));
                        break;
                    default:
                        result.append(c);
                }
            }
        }
        return result.toString();
    }

    private char charFrom(String alphabet) {
        Random rnd = new Random();
        return alphabet.charAt(rnd.nextInt(alphabet.length()));
    }

    public String firstName() {
        Random rnd = new Random();
        var n = rnd.nextInt(200);
        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get("env", "data_generator", "first_names.txt").toFile()))) {
            String line;
            while ((line = br.readLine()) != null && n > 0) {
                n--;
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String lastName() {
        Random rnd = new Random();
        var n = rnd.nextInt(200);
        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get("env", "data_generator", "last_names.txt").toFile()))) {
            String line;
            while ((line = br.readLine()) != null && n > 0) {
                n--;
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
