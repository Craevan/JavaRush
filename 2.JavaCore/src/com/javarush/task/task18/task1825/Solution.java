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

        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (String s : parts) {
                try (FileInputStream fis = new FileInputStream(s)) {
                    byte[] buffer = new byte[fis.available()];
                    while (fis.available() > 0) {
                        int readedBytes = fis.read(buffer);
                        fos.write(buffer, 0, readedBytes);
                    }
                }
            }
        }
    }

    private static String getFileName(String s) {
        String[] words = s.split("\\.");
        return words[0].concat(".").concat(words[1]);
    }
}
