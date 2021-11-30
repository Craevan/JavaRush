package com.javarush.task.pro.task11.task1109;

/* 
Объекты внутренних и вложенных классов
*/

import com.javarush.task.pro.task11.task1109.Outer.Inner;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Outer outer = new Outer();
        Outer.Nested nested = new Outer.Nested();

        Inner inner = outer.new Inner();


    }
}
