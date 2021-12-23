package com.javarush.task.pro.task16.task1619;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* 
Простой шаблон
*/

public class Solution {

    static LocalDateTime localDateTime = LocalDateTime.now();

    public static void main(String[] args) {
        //напишите тут ваш код
        String format = "dd.MM.yyyyг. Hч.mмин";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        System.out.println(dtf.format(localDateTime));
    }
}
