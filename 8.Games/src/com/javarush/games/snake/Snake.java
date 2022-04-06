package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final List<GameObject> snakeParts;

    public Snake(final int x, final int y) {
        snakeParts = new ArrayList<>();
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }
}
