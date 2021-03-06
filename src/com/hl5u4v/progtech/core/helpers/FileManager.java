package com.hl5u4v.progtech.core.helpers;

import com.hl5u4v.progtech.core.Config;
import com.hl5u4v.progtech.core.ErrorHandling.Error_Controller;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileManager {

    @NotNull
    public static Config LoadEnvironment(String envFile) {
        var conf = new Config();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("env", envFile).toFile()))) {
            while (bufferedReader.ready()) {
                var line = bufferedReader.readLine().trim();
                if (!line.startsWith("#")) {
                    try {
                        var split = line.split(":");
                        var value = split[1].trim();
                        switch (split[0].trim().toLowerCase()) {
                            case "appname":
                                conf.setAppName(value);
                                break;
                            case "author":
                                conf.setAuthor(value);
                                break;
                            case "debug":
                                conf.setIsDebug(value.toLowerCase().equals("true"));
                            case "db_host":
                                conf.database().setHost(value);
                                break;
                            case "db_user":
                                conf.database().setUsername(value);
                                break;
                            case "db_password":
                                conf.database().setPassword(value);
                                break;
                            case "db_database":
                                conf.database().setDatabase(value);
                                break;
                            case "db_type":
                                conf.database().setDBType(value);
                                break;
                            default:
                                break;
                        }
                    } catch (Exception ignored) { /* Empty lines, comments etc. will be ignored */ }
                }
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                Error_Controller.Crash(4041, String.format("Environment file \"%s\" not found.", envFile), e);
            } else {
                Error_Controller.Crash(4040, String.format("Could not read environment file \"%s\".", envFile), e);
            }
        }
        return conf;
    }

    public static void writeLines(String dir, String fileName, Object[] items) {
        writeLines(dir, fileName, items, false);
    }

    public static void writeLines(String dir, String fileName, Object[] items, boolean append) {
        try {
            Files.createDirectories(Paths.get("env", dir));
            Path path = Paths.get("env", dir, fileName);
            Charset charSet = StandardCharsets.UTF_8;
            //Files.createFile(path);
            try (var bufferedWriter = Files.newBufferedWriter(path, charSet, StandardOpenOption.CREATE, StandardOpenOption.WRITE, append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING)) {
                for (var item : items) {
                    bufferedWriter.write(item.toString());
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            } catch (Throwable e) {
                e.printStackTrace();
                System.exit(-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void writeFile(String dir, String fileName, String value) {
        writeFile(dir, fileName, value, false);
    }

    public static void writeFile(String dir, String fileName, String value, boolean append) {
        try {
            Files.createDirectories(Paths.get("env", dir));
            Path path = Paths.get("env", dir, fileName);
            Charset charSet = StandardCharsets.UTF_8;
            try (var bufferedWriter = Files.newBufferedWriter(path, charSet, StandardOpenOption.CREATE, StandardOpenOption.WRITE, append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING)) {
                bufferedWriter.write(value, 0, value.length());
                bufferedWriter.newLine();
            } catch (Throwable e) {
                e.printStackTrace();
                System.exit(-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
