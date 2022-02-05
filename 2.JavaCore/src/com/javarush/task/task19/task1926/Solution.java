package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        reader.close();
        reader = new BufferedReader(fileReader);
        String result;
        while (reader.ready()) {
            result = reader.readLine();
            StringBuilder sb = new StringBuilder(result);
            result = sb.reverse().toString();
            System.out.println(result);
        }
        fileReader.close();
        reader.close();
    }
}
