package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
//    private static final Integer ONE = 1;

    public static void main(String[] args) throws Exception {

        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> freq = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);
        reader.close();
        while (inputStream.available() > 0) {
            int key = inputStream.read();
            if (freq.containsKey(key)) {
                int val = freq.get(key);
                freq.replace(key, ++val);
            }
            else
                freq.put(key, 1);
        }
        inputStream.close();
        for (int i : freq.values()) {
            if (i < min)
                min = i;
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue().equals(min))
                System.out.print(entry.getKey() + " ");
        }


    }
}
