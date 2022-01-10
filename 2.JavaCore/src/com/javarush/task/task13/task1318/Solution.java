package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             InputStream is = new FileInputStream(reader.readLine());
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(is))) {
            for (String s = fileReader.readLine(); s != null; s = fileReader.readLine()) {
                System.out.println(s);
            }
        }

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String path = reader.readLine();
//        InputStream is = new FileInputStream(path);
//        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
//        String s;
//        while ((s = fileReader.readLine()) != null) {
//            System.out.println(s);
//        }

    }
}