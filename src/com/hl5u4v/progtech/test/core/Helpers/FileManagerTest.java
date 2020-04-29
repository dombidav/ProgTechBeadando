package com.hl5u4v.progtech.test.core.Helpers;

import com.hl5u4v.progtech.core.Helpers.FileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {


    public static final String TESTING_DIR = "Testing";
    public static final String TEST_TXT = "test.txt";

    @Test
    void loadEnvironment() {
        FileManager.writeLines(TESTING_DIR, "test.env", new String[]{"AppName:TestApp ",
                "Author: TestingUser",
                "\n",
                "# SQL\n",
                "\n",
                "DB_Host: localhost",
                "DB_User: root\n",
                "DB_Password: ",
                "DB_Database: progtech"});
        var actual = FileManager.LoadEnvironment(String.format("%s/test.env", TESTING_DIR));

        assertEquals("TestApp", actual.getAppName());
        assertEquals("TestingUser", actual.getAuthor());
        assertEquals("localhost", actual.Sql().getHost());
        assertEquals("root", actual.Sql().getUsername());
        assertEquals("", actual.Sql().getPassword());
        assertEquals("progtech", actual.Sql().getDatabase());
    }

    @AfterEach
    void tearDown() {
        try {
            Files.walk(Paths.get("app", TESTING_DIR)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        } catch (IOException ignored) {
        }
    }

    @Test
    void writeLinesOverwrite() {
        var expected = new String[]{"Test", "asd"};
        FileManager.writeLines(TESTING_DIR, TEST_TXT, expected);
        FileManager.writeLines(TESTING_DIR, TEST_TXT, expected); //Should overwrite not append

        var file = Paths.get("app", TESTING_DIR, TEST_TXT).toFile();
        assertTrue(file.exists());

        List<String> lines = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    lines.add(line.trim());
                }
            }
            bufferedReader.close();
        } catch (Exception ignored) {
        }

        String[] actual = lines.toArray(new String[]{});

        assertArrayEquals(actual, expected);
    }

    @Test
    void writeLinesAppend() {
        var testArray = new String[]{"Test", "asd"};
        var expected = new String[]{"Test", "asd", "Test", "asd"};

        FileManager.writeLines(TESTING_DIR, TEST_TXT, testArray, true);
        FileManager.writeLines(TESTING_DIR, TEST_TXT, testArray, true); //Should append not overwrite

        var file = Paths.get("app", TESTING_DIR, TEST_TXT).toFile();
        assertTrue(file.exists());

        List<String> lines = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    lines.add(line.trim());
                }
            }
            bufferedReader.close();
        } catch (Exception ignored) {
        }

        String[] actual = lines.toArray(new String[]{});

        assertArrayEquals(actual, expected);
    }
}