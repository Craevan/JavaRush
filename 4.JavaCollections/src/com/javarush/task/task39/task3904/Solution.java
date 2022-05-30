package com.javarush.task.task39.task3904;

import java.util.ArrayList;
import java.util.List;

/* 
Лестница
*/

public class Solution {
    private static int n = 6;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) {
            return 0L;
        }
        if (n == 0) {
            return 1L;
        }
        if (n == 1) {
            return 1L;
        }
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(1L);
        list.add(2L);
        while (n >= list.size()) {
            Long l = list.get(list.size() - 1) + list.get(list.size() - 2) + list.get(list.size() - 3);
            list.add(l);
        }
        long result = list.get(list.size() - 1);
        return result;
    }
}

