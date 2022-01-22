package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        //напишите тут ваш код
        String params = "";
        String[] tmp = url.split("\\?");
        String subUrl = tmp[1];
        tmp = subUrl.split("&");
        for (String s : tmp) {
            if (s.contains("=")) {
                int index = s.indexOf("=");
                String subString = s.substring(0, index);
                params = params.concat(" ").concat(subString);
            }
            else
                params = params.concat(" ").concat(s);
        }
        System.out.println(params.trim());
        if (params.contains("obj")) {
            for (String s : tmp) {
                if (s.contains("obj")) {
                    String[] tmp2 = s.split("=");
                    String par = tmp2[1];
                    try {
                        double d = Double.parseDouble(par);
                        alert(d);
                    }
                    catch (NumberFormatException nfe) {
                        alert(par);
                    }
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
