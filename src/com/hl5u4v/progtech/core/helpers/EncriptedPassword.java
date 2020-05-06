package com.hl5u4v.progtech.core.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptedPassword {
    private String password;

    public EncriptedPassword(String password) {
        this.password = password;
    }

    public static String getHashedPassword(String input) {
        final String SALT = "creative_salt";
        input = String.format("%s$%s", SALT, input);
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

    public boolean verify(String hash) {
        return getHashedPassword(password).equals(hash);
    }

    public String hash() {
        return getHashedPassword(this.password);
    }
}
