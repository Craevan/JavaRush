package com.javarush.games.racer.road;

public class Car extends RoadObject {
    public Car(final RoadObjectType type, final int x, final int y) {
        super(type, x, y);
        speed = 1;
    }
}
