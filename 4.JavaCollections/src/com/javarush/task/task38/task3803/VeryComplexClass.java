package com.javarush.task.task38.task3803;

import java.util.ArrayList;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = new java.util.Date();
        Integer int1 = (Integer) obj;
    }

    public void methodThrowsNullPointerException() {
        ArrayList<String> arrayLis = null;
        arrayLis.add("");
    }

    public static void main(String[] args) {

    }
}
