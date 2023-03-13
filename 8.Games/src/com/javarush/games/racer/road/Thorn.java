package com.javarush.games.racer.road;

public class Thorn extends RoadObject {
    public Thorn(final int x, final int y) {
        super(RoadObjectType.THORN, x, y);
        speed = 0;
    }
}
