package com.javarush.task.task14.task1417;

public class USD extends Money {

    private static final String NAME = "USD";

    public USD(double amount) {
        super(amount);
    }

    @Override
    public String getCurrencyName() {
        return NAME;
    }
}