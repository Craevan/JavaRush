package com.javarush.task.task15.task1517;

/* 
Статики и исключения
*/

public class Solution {
    public static int A = 0;

    static {
        //throw an exception here - выбросьте эксепшн тут
        String test = null;
        if (test.equals("test"))
            System.out.println("112233!");
    }

    public static int B = 5;

    public static void main(String[] args) {
        System.out.println(B);
    }
}
