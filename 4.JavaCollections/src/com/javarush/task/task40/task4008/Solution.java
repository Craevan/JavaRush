package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.y");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
        String[] dateTime = date.split(" ");
        if (dateTime.length == 1 && date.contains(".")) {
            printDateInfo(LocalDate.parse(dateTime[0], dateFormatter));
        }
        if (dateTime.length == 1 && date.contains(":")) {
            printTimeInfo(LocalTime.parse(dateTime[0], timeFormatter));
        }
        if (dateTime.length == 2) {
            printDateInfo(LocalDate.parse(dateTime[0], dateFormatter));
            printTimeInfo(LocalTime.parse(dateTime[1], timeFormatter));
        }
    }

    private static void printTimeInfo(LocalTime lt) {
        System.out.println("AM или PM: " + (lt.getHour() < 12 ? "AM" : "PM"));
        System.out.println("Часы: " + (lt.getHour() > 12 ? lt.getHour() - 12 : lt.getHour()));
        System.out.println("Часы дня: " + lt.getHour());
        System.out.println("Минуты: " + lt.getMinute());
        System.out.println("Секунды: " + lt.getSecond());
    }

    private static void printDateInfo(LocalDate ld) {
        System.out.println("День: " + ld.getDayOfMonth());
        System.out.println("День недели: " + ld.getDayOfWeek().getValue());
        System.out.println("День месяца: " + ld.getDayOfMonth());
        System.out.println("День года: " + ld.getDayOfYear());
        System.out.println("Неделя месяца: " + ld.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
        System.out.println("Неделя года: " + ld.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
        System.out.println("Месяц: " + ld.getMonth().getValue());
        System.out.println("Год: " + ld.getYear());
    }
}
