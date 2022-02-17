package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) {
        String fileName1 = args[0];
        String fileName2 = args[1];
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName1));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2))) {

            while (reader.ready()) {
                String[] words = reader.readLine().split(" ");
                for (String s : words) {
                    if (s.length() > 6)
                        sb.append(s).append(",");
                }
            }
            if (sb.lastIndexOf(",") == sb.length() - 1)
                sb.deleteCharAt(sb.length() - 1);
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
