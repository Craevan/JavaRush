package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return READER.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        System.out.println(Dish.allDishesToString());
        writeMessage("Выберите блюдо: ");
        while (true) {
            String dishForOrder = readString();
            if ("exit".equalsIgnoreCase(dishForOrder))
                break;
            try {
                dishes.add(Dish.valueOf(dishForOrder.toUpperCase()));
                ConsoleHelper.writeMessage(dishForOrder + " added");
            } catch (Exception ex) {
                ConsoleHelper.writeMessage(dishForOrder + " not added");
            }
        }

        return dishes;
    }

}
