package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = reader.readLine();
        String fileName_2 = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName_1);
        FileOutputStream  outputStream = new FileOutputStream(fileName_2);
        List<Integer> bytes = new ArrayList<>();
        while (inputStream.available() > 0) {
            bytes.add(inputStream.read());
        }
        Collections.reverse(bytes);
        for (int i = 0; i < bytes.size(); i++) {
            outputStream.write(bytes.get(i));
        }

        reader.close();
        inputStream.close();
        outputStream.close();

    }
}
