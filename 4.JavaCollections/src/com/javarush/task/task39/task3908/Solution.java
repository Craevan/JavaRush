package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation(""));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        if (s.length() == 1) {
            return true;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        char[] chars = s.toLowerCase().toCharArray();
        for (char ch : chars) {
            charMap.merge(ch, 1, Integer::sum);
        }
        int oddCount = countOfOdd(charMap);
        return oddCount == 0 || oddCount == 1;
    }

    private static int countOfOdd(Map<Character, Integer> map) {
        int result = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                result++;
            }
        }
        return result;
    }
}
