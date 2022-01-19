package com.javarush.task.task14.task1417;

public class Hryvnia extends Money {

    private static final String NAME = "UAH";

    public Hryvnia(double amount) {
        super(amount);
    }

    @Override
    public String getCurrencyName() {
        return NAME;
    }

}
