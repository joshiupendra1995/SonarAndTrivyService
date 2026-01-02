package com.sonartrivy.util;

import java.security.MessageDigest;

public class CryptoUtil {

    public static byte[] encrypt(String input) throws Exception {

        // SAST: Weak hashing algorithm
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        return md.digest();
    }
}

