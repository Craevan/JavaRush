package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);
        List<Integer> integerList = new ArrayList<>();
        while (inputStream.available() > 0) {
            int tmp = inputStream.read();
            if (integerList.contains(tmp))
                continue;
            else
                integerList.add(tmp);
        }
        inputStream.close();
        Collections.sort(integerList);
        for (int i : integerList)
            System.out.print(i + " ");
    }
}
