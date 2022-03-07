package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        boolean hasDigit = false;
        boolean hasCapitalLetter = false;
        boolean hasLetter = false;
        char c;
        while (bos.size() < 8) {
            c = (char) (Math.random() * 126);
            if (Character.isLetterOrDigit(c)) {
                if (Character.isDigit(c))
                    hasDigit = true;
                if (Character.isLowerCase(c))
                    hasLetter = true;
                if (Character.isUpperCase(c))
                    hasCapitalLetter = true;
                bos.write(c);
            }
        }
        if (hasCapitalLetter && hasDigit && hasLetter)
            return bos;
        else
            return getPassword();
    }
}
