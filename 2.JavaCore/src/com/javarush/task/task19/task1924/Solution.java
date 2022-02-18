package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }



    public static void main(String[] args) {
        String fileName = null;
        List<String> lines = new ArrayList<>();
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert fileName != null;
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                while (fileReader.ready()) {
                    lines.add(fileReader.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : lines) {
            String[] words = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String s2 : words) {
                boolean replace = false;
                int indexOfLastElement = s2.length() - 1;
                char[] chars = s2.toCharArray();
                if (!Character.isLetterOrDigit(chars[indexOfLastElement])) {
                    s2 = s2.replace(chars[indexOfLastElement],' ');
                    s2= s2.trim();
                    replace = true;
                }
                try {
                    int i = Integer.parseInt(s2);
                    if (replace)
                        sb.append(map.getOrDefault(i, s2)).append(chars[indexOfLastElement]).append(" ");
                    else
                        sb.append(map.getOrDefault(i, s2)).append(" ");

                } catch (NumberFormatException nfe) {
                    if (replace) {
                        sb.append(s2).append(chars[indexOfLastElement]).append(" ");
                    }
                    else
                        sb.append(s2).append(" ");
                }
            }
            System.out.println(sb.toString().trim());
        }
    }
}
