package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, Double> map = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String line = reader.readLine();
            String[] values = line.split(" ");
            if (map.containsKey(values[0])) {
                double mapValue = map.get(values[0]);
                double res = mapValue + Double.parseDouble(values[1]);
                map.replace(values[0], res);
            }
            else {
                map.put(values[0], Double.parseDouble(values[1]));
            }
        }
        reader.close();
        for (Map.Entry<String, Double> entry : map.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());
    }
}
