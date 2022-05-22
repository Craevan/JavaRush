package com.javarush.task.task36.task3605;

/* 
Использование TreeSet
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


public class Solution {

    public static void main(String[] args) throws IOException {
        Set<Character> result = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int i;
            while ((i = reader.read()) != -1) {
                char ch = (char) Character.toLowerCase(i);
                if (Character.isLetter(ch)) {
                    result.add(ch);
                }
            }
        }
        int count = 0;
        for (char ch : result) {
            System.out.print(ch);
            ++count;
            if (count == 5) {
                break;
            }
        }
    }

}
