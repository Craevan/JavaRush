package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class GameObject {
    public double x;
    public double y;
    public int width;
    public int height;
    public int[][] matrix;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.height = matrix.length;
        this.width = matrix[0].length;
    }

    public void draw(Game game) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                game.setCellValueEx((int) x + j, (int) y + i, Color.values()[matrix[i][j]], "");
            }
        }
    }
}
