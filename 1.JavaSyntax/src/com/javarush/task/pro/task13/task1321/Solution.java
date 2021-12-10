package com.javarush.task.pro.task13.task1321;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getTranslationForDayOfWeek("Вторник"));
        System.out.println(getTranslationForDayOfWeek("Пятница"));
        System.out.println(getTranslationForDayOfWeek("Высплюсенье"));
    }

    public static String getTranslationForDayOfWeek(String ru) {
        String en;
        if ("понедельник".equalsIgnoreCase(ru)) {
            en = "Monday";
        } else if ("вторник".equalsIgnoreCase(ru)) {
            en = "Tuesday";
        } else if ("среда".equalsIgnoreCase(ru)) {
            en = "Wednesday";
        } else if ("четверг".equalsIgnoreCase(ru)) {
            en = "Thursday";
        } else if ("пятница".equalsIgnoreCase(ru)) {
            en = "Friday";
        } else if ("суббота".equalsIgnoreCase(ru)) {
            en = "Saturday";
        } else if ("воскресенье".equalsIgnoreCase(ru)) {
            en = "Sunday";
        } else {
            en = "Недействительный день недели";
        }
        return en;

    }
}
