package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;

public class Controller {
    private final Model model;

    public Controller(final Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    public void onCitySelect(final String cityName) {
        model.selectCity(cityName);
    }
}
