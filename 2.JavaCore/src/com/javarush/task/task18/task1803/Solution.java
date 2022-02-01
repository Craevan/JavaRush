package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {

    private static final Integer ONE = 1;

    public static void main(String[] args) throws Exception {

        int max = Integer.MIN_VALUE;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> freq = new HashMap<>();
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);

        while (inputStream.available() > 0) {
            int key = inputStream.read();
            if (freq.containsKey(key)) {
                int val = freq.get(key);
                freq.replace(key, ++val);
            }
            else
                freq.put(key, ONE);
        }

        inputStream.close();

        for (int i : freq.values()) {
            if (i > max)
                max = i;
        }

        for (Map.Entry entry : freq.entrySet()) {
            if (entry.getValue().equals(max))
                System.out.print(entry.getKey() + " ");
        }

    }
}
