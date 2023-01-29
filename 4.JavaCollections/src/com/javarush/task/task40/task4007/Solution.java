package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {


        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");

    }

    public static void printDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.y");
        SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss");
        String[] partsOfDate = date.split(" ");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        if (partsOfDate.length == 1) {
            if (date.contains(".")) {
                try {
                    calendar.setTime(dateFormat.parse(partsOfDate[0]));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                printDateInfo(calendar);
            } else if (date.contains(":")) {
                try {
                    calendar.setTime(timeFormat.parse(partsOfDate[0]));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                printTimeInfo(calendar);
            }
        }
        if (partsOfDate.length == 2) {
            try {
                calendar.setTime(dateFormat.parse(partsOfDate[0]));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            printDateInfo(calendar);
            try {
                calendar.setTime(timeFormat.parse(partsOfDate[1]));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            printTimeInfo(calendar);
        }
    }

    private static void printTimeInfo(Calendar calendar) {
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == Calendar.PM ? "PM" : "AM"));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Часы дня: " +
                (calendar.get(Calendar.AM_PM) == Calendar.AM
                        ? calendar.get(Calendar.HOUR)
                        : calendar.get(Calendar.HOUR) + 12));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
    }

    private static void printDateInfo(Calendar calendar) {
        System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("День недели: " + (day == 1 ? day + 6 : --day));
        System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("Год: " + calendar.get(Calendar.YEAR));
    }
}
