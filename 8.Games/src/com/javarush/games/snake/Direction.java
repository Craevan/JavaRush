package com.javarush.games.snake;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    private Direction opposite;

    public Direction getOpposite() {
        return opposite;
    }

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }
}
