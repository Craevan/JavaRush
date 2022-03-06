package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        String text = args[2];
        long position = Long.parseLong(args[1]);
        if (raf.length() < position) {
            raf.seek(raf.length());
            raf.write(text.getBytes());
        }
        else {
            raf.seek(position);
            raf.write(text.getBytes());
        }
        raf.close();


    }
}
