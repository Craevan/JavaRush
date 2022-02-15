package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> parts = new ArrayList<>();
        String fileName;
        while (!(fileName = reader.readLine()).equals("end")) {
            parts.add(fileName);
        }
        reader.close();
        Collections.sort(parts);
        File file = new File(getFileName(parts.get(0)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        for (String s : parts) {
            reader = new BufferedReader(new FileReader(s));
            while (reader.ready()) {
                writer.write(reader.readLine());
            }
            reader.close();
            writer.close();
        }
        reader.close();
        writer.close();
    }

    private static String getFileName(String s) {
        String[] words = s.split("\\.");
        return words[0].concat(".").concat(words[1]);
    }
}
