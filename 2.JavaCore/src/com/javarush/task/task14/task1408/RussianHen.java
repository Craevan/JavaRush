package com.javarush.task.task14.task1408;

public class RussianHen extends Hen {

    String country = Country.RUSSIA;

    @Override
    int getCountOfEggsPerMonth() {
        return 10;
    }

    @Override
    String getDescription() {
        return super.getDescription() + " Моя страна - " + country
                + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
