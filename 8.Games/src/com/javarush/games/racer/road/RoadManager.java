package com.javarush.games.racer.road;

import com.javarush.games.racer.RacerGame;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;

    private RoadObject createRoadObject(final RoadObjectType type, final int x, final int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        }
        return null;
    }
}
