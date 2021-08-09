package com.sskj.lib.util;

import java.io.UnsupportedEncodingException;

public class EncodeUtils {
    public static String encodeAES(String content) {
        while (content.length() % 16 != 0) {
            content = content + " ";
        }
        return RxEncryptTool.encryptAES(content, "leidun_ecology_a");
    }

    public static String decryptAES(String content) {
        return RxEncryptTool.decryptAES(content, "leidun_ecology_a");
    }
    public static String encodeAES2B(String content) {
        while (content.length() % 16 != 0) {
            content = content + (char)0;
        }
        String s = null;
        try {
            s = new String(EncryptUtils.encryptAES2Base64(content.getBytes("UTF-8"), "leidun_ecology_a".getBytes("UTF-8"), "AES/ECB/NoPadding", null),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s1 = decryptAES(s);
        return RxEncryptTool.encryptAES(content, "leidun_ecology_a").replace("+","%2B");
//        return s;
    }

}