package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public static void main(String[] args) {

    }

    @Override
    public synchronized int compareTo(Beach beach) {
        double deltaDistance = this.getDistance() - beach.getDistance();
        int deltaQuality = this.getQuality() - beach.getQuality();

        int score_1 = 0;
        int score_2 = 0;

        if (deltaDistance > 0)
            score_2++;
        else if (deltaDistance < 0)
            score_1++;
        if (deltaQuality > 0)
            score_1++;
        else if (deltaQuality < 0)
            score_2++;
        return score_1 - score_2;
    }
}
