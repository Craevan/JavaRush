package com.javarush.task.pro.task18.task1803;

import java.util.Comparator;

/* 
Наставники JavaRush
*/

public class NameComparator implements Comparator<JavaRushMentor> {
    //напишите тут ваш код

    @Override
    public int compare(JavaRushMentor t1, JavaRushMentor t2) {
        if (t1.getName().length() < t2.getName().length())
            return -1;
        else if (t1.getName().length() > t2.getName().length())
            return 1;
        else return 0;
    }
}
