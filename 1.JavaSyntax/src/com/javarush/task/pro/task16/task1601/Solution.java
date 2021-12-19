package com.javarush.task.pro.task16.task1601;

import java.util.Calendar;
import java.util.Date;

/* 
Лишь бы не в понедельник :)
*/

public class Solution {

    static Date birthDate = new Date(88, Calendar.AUGUST, 28);

    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Date date) {
        //напишите тут ваш код
        int dayNumber = date.getDay();
        return switch (dayNumber) {
            case (1) -> "Понедельник";
            case (2) -> "Вторник";
            case (3) -> "Среда";
            case (4) -> "Четверг";
            case (5) -> "Пятница";
            case (6) -> "Суббота";
            case (0) -> "Воскресенье";
            default -> null;
        };
    }
}
