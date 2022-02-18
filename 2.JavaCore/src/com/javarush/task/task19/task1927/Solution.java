package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream original = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream myPrintStream = new PrintStream(byteArrayOutputStream);
        System.setOut(myPrintStream);
        testString.printSomething();
        System.setOut(original);
        String[] lines = byteArrayOutputStream.toString().split("\\n");
        int counter = 0;
        for (String line : lines) {
            System.out.println(line);
            ++counter;
            if (counter == 2) {
                System.out.println("JavaRush - курсы Java онлайн");
                counter = 0;
            }
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
