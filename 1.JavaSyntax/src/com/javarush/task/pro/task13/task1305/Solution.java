package com.javarush.task.pro.task13.task1305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* 
Найти и обезвредить
*/

public class Solution {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("Hello world!");
        words.add("Amigo");
        words.add("Elly");
        words.add("Kerry");
        words.add("Bug");
        words.add("bug");
        words.add("Easy ug");
        words.add("Risha");

        ArrayList<String> copyWordsFirst = new ArrayList<>(words);
        ArrayList<String> copyWordsSecond = new ArrayList<>(words);
        ArrayList<String> copyWordsThird = new ArrayList<>(words);

        removeBugWithFor(copyWordsFirst);
        removeBugWithWhile(copyWordsSecond);
        removeBugWithCopy(copyWordsThird);

        copyWordsFirst.forEach(System.out::println);
        String line = "_________________________";
        System.out.println(line);
        copyWordsSecond.forEach(System.out::println);
        System.out.println(line);
        copyWordsThird.forEach(System.out::println);
        System.out.println(line);
    }

    public static void removeBugWithFor(ArrayList<String> list) {
        //напишите тут ваш код
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i).toLowerCase();
            if ("bug".equals(tmp)) {
                list.remove(i);
                --i;
            }
        }
    }

    public static void removeBugWithWhile(ArrayList<String> list) {
        //напишите тут ваш код
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next().toLowerCase();
            if ("bug".equals(s))
                iterator.remove();
        }
    }

    public static void removeBugWithCopy(ArrayList<String> list) {
        //напишите тут ваш код
        ArrayList<String> tmp = new ArrayList<>(list);
//        Collections.copy(tmp, list);
        for (String s : tmp) {
            String temp = s.toLowerCase();
            if ("bug".equals(temp))
                list.remove(s);
        }
    }
}
