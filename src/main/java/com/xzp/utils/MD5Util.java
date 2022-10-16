package com.xzp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 8:41
 * @Version 1.0
 */

public class MD5Util {
    public static String code(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] bytes = md5.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (int offset=0;offset<bytes.length;offset++){
                i=bytes[offset];
                if(i<0) {
                    i += 256;
                }
                if(i<16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("201014"));
    }
}
