package com.javarush.task.pro.task13.task1316;

public class Solution {

    public static void main(String[] args) {
        //напишите тут ваш код
        JavarushQuest[] jrq = JavarushQuest.values();
        for (JavarushQuest s : jrq) {
            System.out.println(s.ordinal());
        }
    }
}
