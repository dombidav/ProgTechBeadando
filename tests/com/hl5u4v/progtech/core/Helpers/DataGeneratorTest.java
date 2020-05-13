package com.hl5u4v.progtech.core.Helpers;

import com.hl5u4v.progtech.core.helpers.DataGenerator;
import com.hl5u4v.progtech.core.helpers.List2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DataGeneratorTest {

    @Test
    void name() {
        ArrayList<String> actual = new List2<>();
        for (int i = 0; i < 10; i++) {
            actual.add(DataGenerator.get().name());
        }
        for (var name : actual) {
            assertTrue(name.length() > 1);
            assertTrue(name.contains(" "));
        }
    }

    @Test
    void email() {
        ArrayList<String> actual = new List2<>();
        for (int i = 0; i < 10; i++) {
            actual.add(DataGenerator.get().name());
        }
        for (var name : actual) {
            assertTrue(name.length() > 1);
        }
    }

    @Test
    void fromPattern() {
        ArrayList<String> actual = new List2<>();
        for (int i = 0; i < 10; i++) {
            actual.add(DataGenerator.get().fromPattern("a##??"));
        }
        for (var str : actual) {
            assertTrue(str.length() > 1);
            assertTrue(str.startsWith("a"));
        }
    }

    @Test
    void firstName() {
        ArrayList<String> actual = new List2<>();
        for (int i = 0; i < 10; i++) {
            actual.add(DataGenerator.get().firstName());
        }
        for (var name : actual) {
            assertTrue(name.length() > 1);
        }
    }

    @Test
    void lastName() {
        ArrayList<String> actual = new List2<>();
        for (int i = 0; i < 10; i++) {
            actual.add(DataGenerator.get().lastName());
        }
        for (var name : actual) {
            assertTrue(name.length() > 1);
        }
    }
}