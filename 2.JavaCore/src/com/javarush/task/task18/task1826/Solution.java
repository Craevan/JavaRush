package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    private static final int XOR = 3;
    public static void main(String[] args) {
        switch(args[0]) {
            case ("-e"):
            case ("-d"):
                crypt(args[1], args[2]);
                break;
        }
    }

    private static void crypt(String fileName_1, String fileName_2) {
        try {
            FileInputStream inputStream = new FileInputStream(fileName_1);
            FileOutputStream outputStream = new FileOutputStream(fileName_2);

            while (inputStream.available() > 0) {
                int i = inputStream.read();
                outputStream.write(i ^ XOR);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
