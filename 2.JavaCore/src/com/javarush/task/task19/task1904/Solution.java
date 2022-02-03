package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = this.fileScanner.nextLine();
            String[] data = line.split(" ");
            String firstName = data[1];
            String lastName = data[0];
            String middleName = data[2];
            int day = Integer.parseInt(data[3]);
            int month = Integer.parseInt(data[4]) - 1;
            int year = Integer.parseInt(data[5]) - 1900;
            Date bDate = new Date(year, month, day);
            Person person = new Person(firstName, middleName, lastName, bDate);
            return person;
        }

        @Override
        public void close() throws IOException {
            this.fileScanner.close();
        }
    }
}
