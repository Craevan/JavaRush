package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) {
        double biggestValue = 0;
        Map<String, Double> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String[] values = reader.readLine().split(" ");
                if (map.containsKey(values[0])) {
                    double mapValue = map.get(values[0]);
                    double res = mapValue + Double.parseDouble(values[1]);
                    map.replace(values[0], res);
                }
                else {
                    map.put(values[0], Double.parseDouble(values[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() > biggestValue)
                biggestValue = entry.getValue();
        }

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() == biggestValue)
                System.out.println(entry.getKey());
        }
    }
}
