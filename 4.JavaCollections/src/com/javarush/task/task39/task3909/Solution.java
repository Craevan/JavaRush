package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(Solution.isOneEditAway("abc", "aaa"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (isNull(first, second)) {
            return false;
        }
        if (isEquals(first, second)) {
            return true;
        }
        if (isEmpty(first, second)) {
            return true;
        }
        return isOneDeleteAway(first, second) || isOneChangeAway(first, second);
    }

    private static boolean isOneDeleteAway(String first, String second) {
        if (first.length() == second.length()) {
            return false;
        }
        if (Math.abs(first.length() - second.length()) != 1) {
            return false;
        }
        char[] arr1 = first.length() > second.length() ? first.toCharArray() : second.toCharArray();
        char[] arr2 = first.length() < second.length() ? first.toCharArray() : second.toCharArray();
        int index = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (i > arr2.length -1 || arr1[i] != arr2[i]) {
                index = i;
                break;
            }
        }
        char[] tmp = new char[arr2.length];
        for (int i = 0, j = 0; i < arr1.length; i++, j++) {
            if (i == index) {
                j--;
                continue;
            }
            tmp[j] = arr1[i];
        }
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOneChangeAway(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        int index = 0;
        char[] arr1 = first.toCharArray();
        char[] arr2 = second.toCharArray();
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                index = i;
                break;
            }
        }
        arr1[index] = arr2[index];
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEquals(String first, String second) {
        return first.equals(second);
    }

    private static boolean isNull(String first, String second) {
        return first == null || second == null;
    }

    private static boolean isEmpty(String first, String second) {
        return first.isEmpty() || second.isEmpty();
    }
}
