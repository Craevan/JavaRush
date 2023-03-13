package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class GameObject {
    public int x;
    public int y;
    public int[][] matrix;
    public int width;
    public int height;

    public GameObject(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(final int x, final int y, final int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
    }

    public void draw(final Game game) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                game.setCellColor(x + i, y + j, Color.NONE);
            }
        }
    }
}
