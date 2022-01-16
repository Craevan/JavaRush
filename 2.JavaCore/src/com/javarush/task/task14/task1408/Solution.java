package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        hen.getCountOfEggsPerMonth();

    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen = null;
            //напишите тут ваш код
            if (Country.BELARUS.equals(country))
                hen = new BelarusianHen();
            else if (Country.RUSSIA.equals(country))
                hen = new RussianHen();
            else if (Country.UKRAINE.equals(country))
                hen = new UkrainianHen();
            else
                hen = new MoldovanHen();
            return hen;
        }
    }
}
