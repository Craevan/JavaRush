package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        String fileName = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String tmp = reader.readLine();
                String[] words = tmp.split(" ");
                int counter = 0;
                int day = 0;
                int month = 0;
                int year = 0;
                String name = "";
                StringBuilder sb = new StringBuilder();
                for (String s : words) {
                    try {
                        if (counter == 0) {
                            day = Integer.parseInt(s);
                            counter++;
                        }
                        else if (counter == 1) {
                            month = Integer.parseInt(s);
                            counter++;
                        }
                        else if (counter == 2) {
                            year = Integer.parseInt(s);
                            counter = 0;
                        }

                    } catch (NumberFormatException numberFormatException) {
                        sb.append(s).append(" ");
                    }
                    name = sb.toString().trim();
                }
                PEOPLE.add(new Person(name, new Date(year - 1900, month - 1, day)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}