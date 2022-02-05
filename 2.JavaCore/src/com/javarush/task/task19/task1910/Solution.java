package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = reader.readLine();
        String fileName_2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName_1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName_2));
        while (reader.ready()) {
            String s = reader.readLine();
            s = s.replaceAll("\\p{P}","");
            writer.write(s);
        }
        reader.close();
        writer.close();
    }
}
