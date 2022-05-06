package com.javarush.task.task30.task3010;

import java.math.BigInteger;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        String inputLine;
        boolean bool = false;
        for (int i = 2; i < 37; i++) {
            try {
                inputLine = args[0];
                new BigInteger(inputLine, i);
                bool = true;
                System.out.println(i);
                break;
            }
            catch (Exception ignored) {

            }
        }
        if (!bool)
            System.out.println("incorrect");
    }
}