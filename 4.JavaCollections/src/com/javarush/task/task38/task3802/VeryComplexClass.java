package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        FileReader reader = new FileReader("1.txt");
        reader.read();
    }

    public static void main(String[] args) {
    }
}
