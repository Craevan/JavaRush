package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String f1 = reader.readLine();
        String f2 = reader.readLine();
        String f3 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(f1);
        int size = inputStream.available();
        int lessPart = size / 2;
        FileOutputStream outputStream_1 = new FileOutputStream(f2);
        FileOutputStream outputStream_2 = new FileOutputStream(f3);
        if (size % 2 == 0) {
            for (int i = 0; i < lessPart; i++)
                outputStream_1.write(inputStream.read());
            for (int i = 0; i < lessPart; i++)
                outputStream_2.write(inputStream.read());
        }
        else {
            for (int i = 0; i < lessPart + 1; i++)
                outputStream_1.write(inputStream.read());
            for (int i = 0; i < lessPart; i++)
                outputStream_2.write(inputStream.read());
        }

        inputStream.close();
        outputStream_1.close();
        outputStream_2.close();

    }
}
