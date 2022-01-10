package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             OutputStream out = new FileOutputStream(reader.readLine());
             BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(out))) {
            String s;
            while (true) {
                s = reader.readLine();
                fileWriter.write(s + "\n");
                if ("exit".equals(s))
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
