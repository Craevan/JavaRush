package com.javarush.task.task18.task1820;

import java.io.*;

/* 
Округление чисел
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
            String tmp = reader.readLine();
            String[] digits = tmp.split(" ");
            for (String s : digits) {
                int i = (int) Math.round(Double.parseDouble(s));
                writer.write(String.valueOf(i));
                writer.write(" ");
            }
        }
        reader.close();
        writer.close();
    }

}
