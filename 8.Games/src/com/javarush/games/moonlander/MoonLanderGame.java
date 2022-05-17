package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private Rocket rocket;
    private GameObject landscape;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;

    private void check() {

    }

    private void win() {

    }

    private void gameOver() {

    }



    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i, j, Color.BEIGE);
            }
        }
        landscape.draw(this);
        rocket.draw(this);
    }
    
    private void createGame() {
        isUpPressed  = false;
        isLeftPressed = false;
        isRightPressed = false;
        createGameObjects();
        setTurnTimer(50);
        drawScene();
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2.0, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
    }

    @Override
    public void initialize() {
        super.initialize();
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
//        super.onTurn(step);
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                isUpPressed = true;
                break;
            case LEFT:
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case RIGHT:
                isRightPressed = true;
                isLeftPressed = false;
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                isUpPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
            case RIGHT:
                isRightPressed = false;
                break;
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x > 0 && x < WIDTH && y > 0 && y < HEIGHT)
            super.setCellColor(x, y, color);
    }
}
