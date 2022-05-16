package com.javarush.task.task27.task2712.kitchen;

import java.util.StringJoiner;

public enum Dish {

    FISH, STEAK, SOUP, JUICE, WATER;

    public static String allDishesToString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < Dish.values().length; i++) {
            joiner.add(Dish.values()[i].name());
        }
        return joiner.toString();
    }

}
