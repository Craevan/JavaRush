package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 40;

    private RoadManager roadManager;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private FinishLine finishLine;
    private boolean isGameStopped;

    private void createGame() {
        isGameStopped = false;
        roadManager = new RoadManager();
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        finishLine = new FinishLine();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        roadManager.draw(this);
        roadMarking.draw(this);
        player.draw(this);
        finishLine.draw(this);
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

    private void moveAll() {
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        player.move();
        finishLine.move(player.speed);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME OVER", Color.RED, 32);
        stopTurnTimer();
        player.stop();
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

    @Override
    public void onTurn(final int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {
            finishLine.show();
        }
        moveAll();
        roadManager.generateNewRoadObjects(this);
        drawScene();
    }

    @Override
    public void onKeyPress(final Key key) {
        switch (key) {
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case UP:
                player.speed = 2;
                break;
        }
    }

    @Override
    public void onKeyReleased(final Key key) {
        if ((key == Key.RIGHT && player.getDirection() == Direction.RIGHT)
                || (key == Key.LEFT && player.getDirection() == Direction.LEFT)) {
            player.setDirection(Direction.NONE);
        }
        if (key == Key.UP) {
            player.speed = 1;
        }
    }
}
