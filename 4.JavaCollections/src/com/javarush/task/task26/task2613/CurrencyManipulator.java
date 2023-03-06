package com.javarush.task.task26.task2613;

import java.util.Map;

public class CurrencyManipulator {
    private final String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
