package com.javarush.task.task28.task2810.model;


import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private final View view;
    private final Provider[] providers;

    public Model(final View view, final Provider... providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(final String city) {
        final List<Vacancy> vacancies = new ArrayList<>();
        Arrays.stream(providers).forEach(provider -> vacancies.addAll(provider.getJavaVacancies(city)));
        view.update(vacancies);
    }
}
