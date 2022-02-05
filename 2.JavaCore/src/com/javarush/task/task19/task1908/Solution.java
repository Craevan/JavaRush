package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename_1 = reader.readLine();
        String filename_2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(filename_1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename_2));
        while (reader.ready()) {
            String tmp = reader.readLine();
            String[] words = tmp.split("\\p{P}|\\s");
            for (int i = 0; i < words.length;  i++) {
                try {
                    int digit = Integer.parseInt(words[i]);
                    if (i == words.length - 1)
                        writer.write(String.valueOf(digit));
                    else
                        writer.write(digit + " ");
                }
                catch (NumberFormatException ignored) {

                }
            }
        }
        reader.close();
        writer.close();
    }
}
