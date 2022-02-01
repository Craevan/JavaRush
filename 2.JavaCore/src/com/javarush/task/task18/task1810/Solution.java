package com.javarush.task.task18.task1810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        FileInputStream inputStream;
        byte[] buffer = new byte[1000];
        while (true) {
            fileName = reader.readLine();
            inputStream = new FileInputStream(fileName);
            if (inputStream.read(buffer) < 1000) {
                inputStream.close();
                throw new DownloadException();
            }
        }

    }

    public static class DownloadException extends Exception {

    }
}
