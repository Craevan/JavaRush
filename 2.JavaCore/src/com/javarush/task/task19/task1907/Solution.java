package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileReader fileReader = new FileReader(fileName);
        reader = new BufferedReader(fileReader);
        int counter = 0;
        while (reader.ready()) {
            String tmp = reader.readLine();
            String[] words = tmp.split("\\p{Punct}|\\s");
            for (String s : words) {
                if ("world".equalsIgnoreCase(s))
                    ++counter;
            }
        }
        reader.close();
        fileReader.close();
        System.out.println(counter);

    }
}
