package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        String line = null;
        String encodedLine = "";
        char[] encoded;
        if (reader != null) {
            BufferedReader br = new BufferedReader(reader);
            line = br.readLine();
        }
        if (line != null) {
            char[] coded = line.toCharArray();
            encoded = new char[coded.length];
            int i = 0;
            for (char ch : coded) {
                encoded[i] = (char) (ch + key);
                ++i;
            }
            encodedLine = String.valueOf(encoded);
        }

        return encodedLine;
    }
}
