package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = reader.readLine();
        String fileName_2 = reader.readLine();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (FileInputStream fis_1 = new FileInputStream(fileName_1);
            FileInputStream fis_2 = new FileInputStream(fileName_2)) {

            while (fis_2.available() > 0) {
                byteArrayOutputStream.write(fis_2.read());
            }

            while (fis_1.available() > 0) {
                byteArrayOutputStream.write(fis_1.read());
            }

        }

        try (FileOutputStream fos = new FileOutputStream(fileName_1)) {
            byteArrayOutputStream.writeTo(fos);
        }

    }
}
