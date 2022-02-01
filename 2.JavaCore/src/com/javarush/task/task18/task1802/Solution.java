package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int min = Integer.MAX_VALUE;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available() > 0) {
            int b = inputStream.read();
            if (b < min)
                min = b;
        }
        inputStream.close();
        System.out.println(min);
    }
}
