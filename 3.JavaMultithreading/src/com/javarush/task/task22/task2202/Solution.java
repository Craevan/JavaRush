package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();
        String[] words = string.split(" ");
        int[] indexOfSpaces = new int[4];
        char[] chars = string.toCharArray();
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            if (chars[i] == ' ') {
                indexOfSpaces[j] = i;
                ++j;
                if (j == 4)
                    break;
            }
        }
        if (j < 4)
            throw new TooShortStringException();

        return string.substring(indexOfSpaces[0] + 1, indexOfSpaces[3]) + " " + words[4];
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
