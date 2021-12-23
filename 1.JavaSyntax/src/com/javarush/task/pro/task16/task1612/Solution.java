package com.javarush.task.pro.task16.task1612;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/* 
Синтезируем LocalDateTime
*/

public class Solution {

    public static void main(String[] args) {
        Map<LocalDate, List<LocalTime>> dateMap = DateTimeGenerator.generateDateMap();
        printCollection(dateMap.entrySet());

        Set<LocalDateTime> dateSet = convert(dateMap);
        printCollection(dateSet);
    }

    static Set<LocalDateTime> convert(Map<LocalDate, List<LocalTime>> sourceMap) {
        //напишите тут ваш код
        Set<LocalDateTime> innerSet = new HashSet<>();
        List<LocalTime> timeList = new ArrayList<>();
        LocalDate date;
        LocalTime time;
        for (Map.Entry<LocalDate, List<LocalTime>> map : sourceMap.entrySet()) {
            timeList.clear();
            timeList = map.getValue();
            for (LocalTime tmp : timeList) {
                date = map.getKey();
                time = tmp;
                innerSet.add(LocalDateTime.of(date, time));
            }

        }

        return innerSet;
    }

    static void printCollection(Collection<?> collection) {
        System.out.println("-----------------------------------------------------");
        collection.forEach(System.out::println);
    }
}