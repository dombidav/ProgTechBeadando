package com.hl5u4v.progtech.core.Helpers;

import com.hl5u4v.progtech.core.helpers.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayUtilsTest {

    @Test
    void concat() {
        var expected1 = new int[] {0, 8, 5, 3, 10};
        var expected2 = new String[] {"a", "asd", "c", "e", "iuz"};

        var actual1 = ArrayUtils.concat(new int[]{ 0, 8, 5}, new int[]{ 3, 10});
        var actual2 = ArrayUtils.concat(new String[] { "a", "asd" }, new String[] { "c", "e", "iuz" });

        assertArrayEquals(actual1, expected1);
        assertArrayEquals(actual2, expected2);
    }
}