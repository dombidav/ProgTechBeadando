package com.hl5u4v.progtech.core.Helpers;

import com.hl5u4v.progtech.core.helpers.EncriptedPassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncriptedPasswordTest {

    @Test
    void getHashedPassword() {
        var expected = "8d000f126e846010d91a84f3229a44091e66a59d"; //"password"
        var actual = EncriptedPassword.getHashedPassword("password");

        assertEquals(expected, actual);
    }

    @Test
    void verify() {
        assertTrue(new EncriptedPassword("password").verify("8d000f126e846010d91a84f3229a44091e66a59d"));
    }
}