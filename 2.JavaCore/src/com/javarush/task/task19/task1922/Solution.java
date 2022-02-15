package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        String fileName = reader.readLine();
        int counter = 0;
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            String tmp = reader.readLine();
            String[] splitedLine = tmp.split(" ");
            for (String word : splitedLine) {
                if(words.contains(word))
                    ++counter;
            }

            if (counter == 2)
                lines.add(tmp);

            counter = 0;
        }
        reader.close();

        for (String s : lines)
            System.out.println(s);
    }
}
