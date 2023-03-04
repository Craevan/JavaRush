package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

public class HtmlView implements View {
    private Controller controller;



    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(final List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }
}
