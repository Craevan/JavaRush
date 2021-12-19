package com.javarush.task.pro.task16.task1607;

import java.time.LocalDate;
import java.util.Date;

/* 
Освоение нового API
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(nowExample());
        System.out.println(ofExample());
        System.out.println(ofYearDayExample());
        System.out.println(ofEpochDayExample());
    }

    static LocalDate nowExample() {
        //напишите тут ваш код

        return LocalDate.now();
    }

    static LocalDate ofExample() {
        //напишите тут ваш код

        return LocalDate.of(2020, 9, 12);
    }

    static LocalDate ofYearDayExample() {
        //напишите тут ваш код
        LocalDate date = ofExample();
        int dayOfYear = date.getDayOfYear();

        return LocalDate.ofYearDay(2020, dayOfYear);
    }

    static LocalDate ofEpochDayExample() {
        //напишите тут ваш код
        return LocalDate.ofEpochDay(ofExample().toEpochDay());
    }
}
