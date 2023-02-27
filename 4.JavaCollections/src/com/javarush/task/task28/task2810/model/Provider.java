package com.javarush.task.task28.task2810.model;

public class Provider {
    private Strategy strategy;

    public Provider(final Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }
}
