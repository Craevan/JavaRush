package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private final String projectSourceFolder = "./4.JavaCollections/src/";
    private final String packages = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    private final String fileName = "vacancies.html";
    private final String filePath = projectSourceFolder + packages + "/" + fileName;
    private Controller controller;

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(final List<Vacancy> vacancies) {
        return ""; //STUB
    }

    private void updateFile(final String fileBody) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }
}
