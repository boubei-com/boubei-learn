package com.boubei.learn.jk.security;

import java.io.IOException;

/** 
 * <p> WeakEncode.java </p> 
 * 
 * @author Jon.King 2007-9-27
 * 
 * 利用base64 进行弱加密解密
 */
public class WeakEncode {

    /**
     * Encode a string using Base64 encoding. Used when storing passwords as
     * cookies.
     * 
     * 这是一种弱加密方式，任何人都可以用BASE64Decoder进行解密
     * 
     * @param str
     * @return String
     */
    public static String encodeString(String str) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes()).trim();
    }   

    /**
     * Decode a string using Base64 encoding.
     * 
     * @param str
     * @return String
     */
    public static String decodeString(String str) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            return new String(decoder.decodeBuffer(str));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe.getCause());
        }
    }

    public static void main(String[] args) {
        System.out.println(encodeString("jinpujun"));
        System.out.println(decodeString("amlucHVqdW4="));
    }
}

