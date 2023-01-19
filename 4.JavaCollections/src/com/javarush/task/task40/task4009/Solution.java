package com.javarush.task.task40.task4009;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        Locale italy = Locale.ITALIAN;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy", italy);
        LocalDate localDate = LocalDate.parse(birthday, dtf);
        localDate = localDate.with(Year.parse(year));
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(italy)
                .format(localDate)
                .split(" ")[0];
    }
}
