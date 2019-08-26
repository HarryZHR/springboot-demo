package com.zhr.student.common.util;

import java.security.MessageDigest;

public class MD5EncryptUtils {

    public static String  md5Encrypt(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = string.getBytes("utf-8");
            byte[] buff = md.digest(input);
            return HexUtils.bytes2Hex(buff).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
