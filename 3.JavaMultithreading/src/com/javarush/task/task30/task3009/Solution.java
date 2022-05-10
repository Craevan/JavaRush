package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        String digit;
        for (int i = 2; i < 37; i++) {
            try {
                digit = Integer.toString(Integer.parseInt(number), i);
                if (isPalindrome(digit)) {
                    result.add(i);
                }
            } catch (NumberFormatException ignored) {

            }
        }
        return result;
    }

    private static boolean isPalindrome(String number) {
        return number.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(number.replaceAll("\\W", ""))
                        .reverse().toString());
    }
}