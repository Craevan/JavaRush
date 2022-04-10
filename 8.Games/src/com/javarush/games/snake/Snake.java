package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    public boolean isAlive = true;

    private Direction direction = Direction.LEFT;
    private static final String HEAD_SIGN = "\\uD83D\\uDC7E";
    private static final String BODY_SIGN = "\\u26AB";

    private final List<GameObject> snakeParts;

    public Snake(final int x, final int y) {
        snakeParts = new ArrayList<>();
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {

    }

    public GameObject createNewHead() {
        switch (direction) {
            case UP:
                return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
            case DOWN:
                return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
            case LEFT:
                return new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
            case RIGHT:
                return new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
        }
        return null;
    }

    public void removeTail() {
        GameObject tail = snakeParts.get(snakeParts.size() - 1);
        snakeParts.remove(tail);
    }

    public void draw(Game game) {

        for (GameObject gameObject : snakeParts) {
            if (gameObject.equals(snakeParts.get(0))) {
                if (isAlive)
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                else
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, HEAD_SIGN, Color.RED, 75);
            }
            else {
                if (isAlive)
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
                else
                    game.setCellValueEx(gameObject.x, gameObject.y, Color.NONE, BODY_SIGN, Color.RED, 75);
            }
        }

    }

}
