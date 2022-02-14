package com.javarush.task.task18.task1821;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        int[] ascii  = new int[128];

        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                ascii[fileReader.read()]++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ascii.length; i++) {
            if (ascii[i] != 0)
                System.out.println((char) i + " " + ascii[i]);
        }

    }
}
