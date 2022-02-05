package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        testString.printSomething();
        String s = outputStream.toString();
        int result = 0;
        String[] strings = s.split(" ");
        int a = Integer.parseInt(strings[0]);
        int b = Integer.parseInt(strings[2]);
        switch (strings[1]) {
            case ("+") :
                result = a + b;
                break;
            case ("*") :
                result = a * b;
                break;
            case ("-") :
                result = a - b;
                break;
        }
        System.setOut(defaultStream);
        System.out.println(s + result);



    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

