package com.hl5u4v.progtech.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class APPTest {

    @Test
    void getRoutes() {
        APP.start();
        var x = assertDoesNotThrow(APP::getRoutes);
        assertEquals(17, x.size());
    }

    @Test
    void start() {
        assertDoesNotThrow(APP::start);
    }
}