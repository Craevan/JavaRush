package com.javarush.games.racer;

import com.javarush.games.racer.road.RoadManager;

public class PlayerCar extends GameObject {
    private static final int playerCarHeight = ShapeMatrix.PLAYER.length;

    private Direction direction;
    public int speed = 1;

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
            case LEFT:
                this.x--;
                break;
            case RIGHT:
                this.x++;
                break;
        }
        if (x < RoadManager.LEFT_BORDER) {
            x = RoadManager.LEFT_BORDER;
        }
        if (x > RoadManager.RIGHT_BORDER - width) {
            x = RoadManager.RIGHT_BORDER - width;
        }
    }
}
