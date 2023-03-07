package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        final String currencyCode = ConsoleHelper.askCurrencyCode();
        final String[] nominalAndCount = ConsoleHelper.getValidTwoDigits(currencyCode);
        final CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        currencyManipulator.addAmount(Integer.parseInt(nominalAndCount[0]), Integer.parseInt(nominalAndCount[1]));
        System.out.println(currencyManipulator.getTotalAmount());
    }
}
