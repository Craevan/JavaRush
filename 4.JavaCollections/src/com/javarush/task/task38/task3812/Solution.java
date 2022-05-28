package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static <E> boolean printFullyQualifiedNames(Class<E> c) {
        if (!c.isAnnotationPresent(PrepareMyTest.class)) {
            return false;
        }
        PrepareMyTest test = c.getAnnotation(PrepareMyTest.class);
        for (String s : test.fullyQualifiedNames()) {
            System.out.println(s);
        }
        return true;
    }

    public static <E> boolean printValues(Class<E> c) {
        if (!c.isAnnotationPresent(PrepareMyTest.class)) {
            return false;
        }
        PrepareMyTest test = c.getAnnotation(PrepareMyTest.class);
        for (Class<?> clazz : test.value()) {
            System.out.println(clazz.getSimpleName());
        }
        return true;
    }
}
