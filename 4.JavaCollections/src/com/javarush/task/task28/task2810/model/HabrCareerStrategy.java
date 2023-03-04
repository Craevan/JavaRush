package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy {
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&page=%d";

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0")
                .referrer("https://career.habr.com/")
                .get();
    }

    @Override
    public List<Vacancy> getVacancies(final String searchString) {
        final List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        try {
            do {
                final Document doc = getDocument(searchString, page);
                final Elements vacanciesList = doc.getElementsByClass("job");
                if (vacanciesList.isEmpty()) {
                    break;
                }
                for (Element element : vacanciesList) {
                    Elements title = element.getElementsByClass("title");
                    Elements links = title.get(0).getElementsByTag("a");
                    Elements locations = element.getElementsByClass("location");
                    Elements companyName = element.getElementsByClass("company_name");
                    Elements salary = element.getElementsByClass("count");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName("career.habr.com");
                    vacancy.setTitle(links.get(0).text());
                    vacancy.setUrl("https://career.habr.com" + links.get(0).attr("href"));
                    vacancy.setCity(locations.size() > 0 ? locations.get(0).text() : "");
                    vacancy.setCompanyName(companyName.get(0).text());
                    vacancy.setSalary(salary.size() > 0 ? salary.get(0).text() : "");

                    vacancies.add(vacancy);
                }
                page++;
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }
}
