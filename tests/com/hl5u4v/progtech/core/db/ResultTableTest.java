package com.hl5u4v.progtech.core.db;

import com.hl5u4v.progtech.core.helpers.List2;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ResultTableTest {

    @Test
    void getFieldNames() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");

        assertArrayEquals(new String[]{"test", "test2"}, x.getFieldNames().toArray());
    }

    @Test
    void addField() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");

        assertArrayEquals(new String[]{"test", "test2"}, x.getFieldNames().toArray());
    }

    @Test
    void add() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");
        x.addField("test3");

        List2<Object> testList1 = new List2<>(new Object[]{"value1", 2, true});
        List2<Object> testList2 = new List2<>(new Object[]{"value2", 3, false});
        List2<Object> testList3 = new List2<>(new Object[]{"value2", 3, false, "this will be ignored"});

        assertDoesNotThrow(() -> x.add(testList1));
        assertDoesNotThrow(() -> x.add(testList2));
        assertDoesNotThrow(() -> x.add(testList3));

        assertEquals(3, x.all().size());
    }

    @Test
    void getRecord() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");
        x.addField("test3");

        List2<Object> testList1 = new List2<>(new Object[]{"value1", 2, true});
        List2<Object> testList2 = new List2<>(new Object[]{"value3", 3});

        assertDoesNotThrow(() -> x.add(testList1));
        assertDoesNotThrow(() -> x.add(testList2));

        assertEquals("value1", x.getRecord(0).get("test"));
        assertEquals(2, x.getRecord(0).get("test2"));
        assertEquals(true, x.getRecord(0).get("test3"));
        assertNull(assertDoesNotThrow(() -> x.getRecord(0).get("test4")));

        assertEquals("value3", x.getRecord(1).get("test"));
        assertEquals(3, x.getRecord(1).get("test2"));
        assertNull(x.getRecord(1).get("test3"));
    }

    @Test
    void getFrom() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");
        x.addField("test3");

        List2<Object> testList1 = new List2<>(new Object[]{"value1", 2, true});
        List2<Object> testList2 = new List2<>(new Object[]{"value3", 3});

        assertDoesNotThrow(() -> x.add(testList1));
        assertDoesNotThrow(() -> x.add(testList2));

        assertArrayEquals(new Object[]{"value1", "value3"}, x.getFrom("test").toArray());
        assertArrayEquals(new Object[]{2, 3}, x.getFrom("test2").toArray());
        assertArrayEquals(new Object[]{true, null}, x.getFrom("test3").toArray());
    }

    @Test
    void all() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");
        x.addField("test3");

        List2<Object> testList1 = new List2<>(new Object[]{"value1", 2, true});
        List2<Object> testList2 = new List2<>(new Object[]{"value3", 3});

        assertDoesNotThrow(() -> x.add(testList1));
        assertDoesNotThrow(() -> x.add(testList2));

        var expected = new List2<HashMap<String, Object>>();
        var temp = new HashMap<String, Object>();
        temp.put("test", "value1");
        temp.put("test2", 2);
        temp.put("test3", true);

        expected.add(temp); //temp.clear(); <-- this does nothing... sure...
        temp = new HashMap<>();

        temp.put("test", "value3");
        temp.put("test2", 3);

        expected.add(temp);

        assertEquals(expected, x.all());
    }

    @Test
    void getFieldName() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");
        x.addField("test3");

        assertEquals("test", x.getFieldName(0));
        assertEquals("test2", x.getFieldName(1));
        assertEquals("test3", x.getFieldName(2));
    }

    @Test
    void first() {
        var x = new ResultTable();
        x.addField("test");
        x.addField("test2");
        x.addField("test3");

        List2<Object> testList1 = new List2<>(new Object[]{"value1", 2, true});
        List2<Object> testList2 = new List2<>(new Object[]{"value3", 3});

        assertDoesNotThrow(() -> x.add(testList1));
        assertDoesNotThrow(() -> x.add(testList2));

        HashMap<String, Object> expected = new HashMap<>();
        expected.put("test", "value1");
        expected.put("test2", 2);
        expected.put("test3", true);

        assertEquals(expected, x.first());
    }
}