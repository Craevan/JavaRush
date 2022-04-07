package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final String HEAD_SIGN = "\\uD83D\\uDC7E";
    private static final String BODY_SIGN = "\\u26AB";

    private final List<GameObject> snakeParts;

    public Snake(final int x, final int y) {
        snakeParts = new ArrayList<>();
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {

        for (GameObject gameObject : snakeParts) {
            if (gameObject.equals(snakeParts.get(0)))
                game.setCellValue(gameObject.x, gameObject.y, HEAD_SIGN);
            else
                game.setCellValue(gameObject.x, gameObject.y, BODY_SIGN);
        }

    }

}
