package com.hl5u4v.progtech.core.Helpers;

import com.hl5u4v.progtech.core.helpers.List2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class List2Test {

    @Test
    void firstOr() {
        var x = new List2<String>();
        assertEquals("fail", x.firstOr("fail"));

        x.add("asd");
        x.add("test");
        x.add("random");

        assertEquals("asd", x.firstOr("fail"));
    }

    @Test
    void lastOr() {
        var x = new List2<String>();
        assertEquals("fail", x.firstOr("fail"));

        x.add("asd");
        x.add("test");
        x.add("random");

        assertEquals("asd", x.firstOr("fail"));
    }

    @Test
    void get() {
        var x = new List2<String>();

        assertEquals("fail", x.get(0, "fail"));

        x.add("asd");
        x.add("test");
        x.add("random");

        assertEquals("asd", x.get(0));
        assertEquals("test", x.get(1));
        assertEquals("random", x.get(2));
        assertEquals("fail", x.get(3, "fail"));
    }

    @Test
    void any() {
        var x = new List2<String>();

        x.add("asd");
        x.add("test");
        x.add("random");

        assertTrue(x.any(item -> item.contains("t")));
        assertTrue(x.any(item -> item.contains("a")));
        assertFalse(x.any(item -> item.equals("123")));
    }

    @Test
    void all() {
        var x = new List2<String>();

        x.add("asd");
        x.add("test");
        x.add("random");

        assertTrue(x.all(item -> item.length() > 1));
        assertFalse(x.all(item -> item.length() > 4));
    }

    @Test
    void take() {
        var x = new List2<String>();

        x.add("asd");
        x.add("test");
        x.add("random");

        assertArrayEquals(new String[]{"asd"}, x.take(1).toArray());
        assertArrayEquals(new String[]{"asd", "test"}, x.take(2).toArray());
        assertArrayEquals(new String[]{"asd", "test", "random"}, x.take(3).toArray());
        assertThrows(IndexOutOfBoundsException.class, () -> x.take(4).toArray());
    }

    @Test
    void skip() {
        var x = new List2<String>();

        x.add("asd");
        x.add("test");
        x.add("random");

        assertArrayEquals(new String[]{"test", "random"}, x.skip(1).toArray());
        assertArrayEquals(new String[]{"random"}, x.skip(2).toArray());
        assertArrayEquals(new String[]{}, x.skip(3).toArray());
        assertArrayEquals(new String[]{}, assertDoesNotThrow(() -> x.skip(4).toArray()));
    }

    @Test
    void where() {
        var x = new List2<String>();

        x.add("asd");
        x.add("test");
        x.add("random");

        assertArrayEquals(new String[]{"asd", "test"}, x.where(item -> item.contains("s")).toArray());
        assertArrayEquals(new String[]{"asd", "random"}, x.where(item -> item.contains("a")).toArray());
        assertArrayEquals(new String[]{}, x.where(item -> item.contains("q")).toArray());
    }

    @Test
    void select() {
        var x = new List2<String>();

        x.add("0");
        x.add("10");
        x.add("22");

        assertArrayEquals(new Integer[]{0, 10, 22}, x.select(Integer::parseInt).toArray());
        assertArrayEquals(new Boolean[]{false, false, true}, x.select(item -> (Integer.parseInt(item)) > 10).toArray());
    }
}