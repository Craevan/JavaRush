package com.javarush.task.pro.task18.task1802;

import java.util.Comparator;

/* 
Сортировка по возрасту
*/

public class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student student, Student t1) {
        if (student.getAge() == t1.getAge())
            return 0;
        else if (student.getAge() > t1.getAge())
            return -1;
        else
            return 1;
    }
}
