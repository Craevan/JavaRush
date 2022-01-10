package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             InputStream is = new FileInputStream(reader.readLine());
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(is))) {
            List<Integer> integers = new ArrayList<>();
            while (fileReader.ready()) {
                integers.add(Integer.parseInt(fileReader.readLine()));
            }
            Collections.sort(integers);
            for (int tmp : integers) {
                if (tmp % 2 == 0)
                    System.out.println(tmp);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
