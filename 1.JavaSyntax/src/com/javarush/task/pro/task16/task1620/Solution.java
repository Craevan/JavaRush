package com.javarush.task.pro.task16.task1620;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/* 
Еще один простой шаблон
*/

public class Solution {

    static ZonedDateTime zonedDateTime = ZonedDateTime.now();

    public static void main(String[] args) {
        //напишите тут ваш код
        String format = "e d.M.yy HH:mm:ss.n VV";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        System.out.println(dtf.format(zonedDateTime));
    }
}
