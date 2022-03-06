package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest mdAlgo = MessageDigest.getInstance("MD5");
        mdAlgo.update(byteArrayOutputStream.toByteArray());

        byte[] input = mdAlgo.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            String s = Integer.toHexString(0xFF & input[i]);
            if (s.length() < 2)
                s = "0" + s;
            sb.append(s);
        }


        return sb.toString().equals(md5);
    }
}
