package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private List<Star> stars;

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }

        for (Star star : stars) {
            star.draw(this);
        }
    }

    private void drawScene() {
        drawField();
    }

    private void createGame() {
        createStars();
        drawScene();
    }

    private void createStars() {
        stars = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(random.nextInt(SpaceInvadersGame.WIDTH + 1),
                    random.nextInt(SpaceInvadersGame.WIDTH + 1)));
        }
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
}
