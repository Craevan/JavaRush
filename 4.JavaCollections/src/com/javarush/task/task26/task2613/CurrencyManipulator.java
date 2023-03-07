package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private final String currencyCode;
    private final Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.merge(denomination, count, Integer::sum);
    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            result += entry.getKey() * entry.getValue();
        }
        return result;
    }
}
