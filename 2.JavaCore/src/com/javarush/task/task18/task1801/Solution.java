package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName);
        int max = Integer.MIN_VALUE;
        while (inputStream.available() > 0) {
            int b = inputStream.read();
            if (b > max)
                max = b;
        }
        inputStream.close();
        System.out.println(max);


    }
}
