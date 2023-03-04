package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private String getUpdatedFileContent(final List<Vacancy> vacancies) {
        try {
            Document doc = getDocument();
            Elements elements = doc.getElementsByClass("template");
            Element copy = elements.clone().removeAttr("style").removeClass("template").get(0);
            Elements oldVacancies = doc.getElementsByClass("vacancy");

            oldVacancies.forEach(vacancy -> {
                if (!vacancy.hasClass("template")) {
                    vacancy.remove();
                }
            });

            vacancies.forEach(vacancy -> {
                Element vacEl = copy.clone();

                Element vacLink = vacEl.getElementsByAttribute("href").get(0);
                vacLink.appendText(vacancy.getTitle());
                vacLink.attr("href", vacancy.getUrl());

                Element city = vacEl.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());

                Element company = vacEl.getElementsByClass("companyName").get(0);
                company.appendText(vacancy.getCompanyName());

                Element salary = vacEl.getElementsByClass("salary").get(0);
                salary.appendText(vacancy.getSalary());

                elements.before(vacEl.outerHtml());
            });

            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
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
