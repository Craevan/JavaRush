package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int current;
        int prev = Integer.MAX_VALUE;
        int num = 0;
        for (char ch : chars) {
            current = convert(ch);
            num += current;
            if (prev < current) {
                num = num - (2 * prev);
            }
            prev = current;
        }
        return num;
    }

    private static int convert(char ch) {
        switch (Character.toLowerCase(ch)) {
            case('i') :
                return 1;
            case ('v') :
                return 5;
            case ('x') :
                return 10;
            case ('l') :
                return 50;
            case ('c') :
                return 100;
            case ('d') :
                return 500;
            case ('m') :
                return 1000;
        }
        return 0;
    }
}
