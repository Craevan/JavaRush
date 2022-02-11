package com.javarush.task.task18.task1817;

import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(args[0]);
        int spaceCounter = 0;
        int counter = 0;
        while (fileReader.ready()) {
            if (fileReader.read() == 32)
                spaceCounter++;
            counter++;
        }
        fileReader.close();
        double prop = (double) spaceCounter / (double) counter * 100;
        System.out.printf("%.2f", prop);

    }
}
