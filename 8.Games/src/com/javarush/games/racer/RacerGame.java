package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;

    private void createGame() {
        drawScene();
    }

    private void drawScene() {
        drawField();
    }

    private void drawField() {
        for (int x = 0; x < RacerGame.WIDTH; x++) {
            for (int y = 0; y < RacerGame.HEIGHT; y++) {
                if (x < RacerGame.ROADSIDE_WIDTH ||
                        (x >= RacerGame.WIDTH - RacerGame.ROADSIDE_WIDTH)) {
                    setCellColor(x, y, Color.GREEN);
                } else if (x == CENTER_X) {
                    setCellColor(x, y, Color.WHITE);
                } else {
                    setCellColor(x, y, Color.DIMGREY);
                }
            }
        }
    }

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void setCellColor(final int x, final int y, final Color color) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }
        super.setCellColor(x, y, color);
    }
}
