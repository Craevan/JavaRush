package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in;

        while (!("exit".equals(in = reader.readLine()))) {
            if (in.contains(".")) {
                try {
                    Double d = Double.parseDouble(in);
                    print(d);
                } catch (NumberFormatException nfe) {
                    print(in);
                }
            } else {
                try {
                    Integer i = Integer.parseInt(in);
                    if (i > 0 && i < 128) {
                        short sh = Short.parseShort(String.valueOf(i));
                        print(sh);
                    } else
                        print(i);
                } catch (NumberFormatException nfe) {
                    print(in);
                }
            }
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
