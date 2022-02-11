package com.javarush.task.task18.task1816;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(args[0]));
        int counter = 0;
        while (inputStreamReader.ready()) {
            int bytes = inputStreamReader.read();
            if ((bytes > 64 && bytes < 91) || (bytes > 96 && bytes < 123))
                ++counter;
        }
        System.out.println(counter);
        inputStreamReader.close();
    }
}
