package com.example.aaronwang.and2ard.utils;

/**
 * Created by aaronwang on 2018/3/21.
 */

public class StringUtils {
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
