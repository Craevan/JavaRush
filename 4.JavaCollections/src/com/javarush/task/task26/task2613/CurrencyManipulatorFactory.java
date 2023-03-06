package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static final Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator manipulator = map.get(currencyCode.toLowerCase());
        if (manipulator != null) {
            return manipulator;
        }
        manipulator = new CurrencyManipulator(currencyCode.toLowerCase());
        map.put(currencyCode.toLowerCase(), manipulator);
        return manipulator;
    }

    private CurrencyManipulatorFactory() {
    }
}
