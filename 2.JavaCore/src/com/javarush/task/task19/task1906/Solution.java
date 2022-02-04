package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1 = reader.readLine();
        String fileName_2 = reader.readLine();
        FileReader fis = new FileReader(fileName_1);
        FileWriter fos = new FileWriter(fileName_2);
        int i = 1;
        while (fis.ready()) {
            if (i % 2 == 0)
                fos.write(fis.read());
            else
                fis.read();
            ++i;
        }
        reader.close();
        fis.close();
        fos.close();
    }
}
