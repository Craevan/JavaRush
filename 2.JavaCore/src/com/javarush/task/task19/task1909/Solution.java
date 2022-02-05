package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileNameRead = reader.readLine();
        String fileNameWrite = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileNameRead));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameWrite));
        while (reader.ready()) {
            String s = reader.readLine();
            if (s.contains("."))
                s = s.replace(".", "!");
            writer.write(s + '\n');
        }
        reader.close();
        writer.close();

    }
}
