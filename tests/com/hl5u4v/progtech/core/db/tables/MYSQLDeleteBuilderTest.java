package com.hl5u4v.progtech.core.db.tables;

import com.hl5u4v.progtech.core.APP;
import com.hl5u4v.progtech.core.db.Schema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MYSQLDeleteBuilderTest {

    @BeforeEach
    void setUp() {
        APP.start();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void where() {
        var actual = Schema.table("user").delete().where("id", "=", "1");
        var expected = new ArrayList<>();
        expected.add("1");
        assertEquals("DELETE FROM user WHERE  `user`.`id` = ?", actual.getQuery());
        assertArrayEquals(expected.toArray(), actual.getParameters().toArray());
    }

    @Test
    void orWhere() {
        var actual = Schema.table("user").delete().where("id", "=", "1").orWhere("name", "=", "test");
        var expected = new ArrayList<>();
        expected.add("1");
        expected.add("test");
        assertEquals("DELETE FROM user WHERE  `user`.`id` = ? OR `user`.`name` = ?", actual.getQuery());
        assertArrayEquals(expected.toArray(), actual.getParameters().toArray());
    }

    @Test
    void orderBy() {
    }

    @Test
    void descending() {
    }

    @Test
    void orderByRandom() {
    }

    @Test
    void limit() {
    }

}