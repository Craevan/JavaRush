package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return bis.readLine();
        } catch (IOException ignore) {

        }
        return "";
    }

    public static String askCurrencyCode() {
        String currency;
        do {
            writeMessage("Введите код валюты:");
            currency = readString();
            if (currency.length() != 3) {
                writeMessage("Введен некорректный код валюты");
            }
        }
        while (currency.length() != 3);
        return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        String[] result;
        while (true) {
            writeMessage("Введите номинал и количество банкнот");
            String input = readString();
            result = input.split(" ");
            int nominal = -1;
            int count = -1;
            if (result.length == 2) {
                try {
                    nominal = Integer.parseInt(result[0]);
                    count = Integer.parseInt(result[1]);
                } catch (NumberFormatException nfe) {
                    writeMessage("Введены некорректные данные");
                    return getValidTwoDigits(currencyCode);
                }
            }
            if (count > 0 && nominal > 0) {
                break;
            }
            writeMessage("Введены некорректные данные");
        }
        return result;
    }
}
