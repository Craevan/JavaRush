package com.javarush.task.task14.task1408;

public class MoldovanHen extends Hen {

    String country = Country.MOLDOVA;

    @Override
    int getCountOfEggsPerMonth() {
        return 5;
    }

    @Override
    String getDescription() {
        return super.getDescription() + " Моя страна - " + country
                + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }

}
