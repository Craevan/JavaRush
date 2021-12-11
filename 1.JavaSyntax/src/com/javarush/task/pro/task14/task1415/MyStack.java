package com.javarush.task.pro.task14.task1415;

import java.util.ArrayList;
import java.util.List;

/* 
Стек в домашних условиях
*/

public class MyStack {

    private final List<String> storage = new ArrayList<>();

    public void push(String s) {
        //напишите тут ваш код
        storage.add(0, s);
    }

    public String pop() {
        //напишите тут ваш код
        String tmp = storage.get(0);
        storage.remove(0);
        return tmp;
    }

    public String peek() {
        //напишите тут ваш код
        return storage.get(0);
    }

    public boolean empty() {
        //напишите тут ваш код
        return storage.isEmpty();
    }

    public int search(String s) {
        //напишите тут ваш код
        return storage.indexOf(s);
    }
}
