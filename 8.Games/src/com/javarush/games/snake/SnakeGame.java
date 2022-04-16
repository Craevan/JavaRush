package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game  {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private boolean isGameStopped;

    private void createGame() {
        isGameStopped = false;
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
//        apple = new Apple(5, 5); // теперь apple создается в методе createNewApple()
        createNewApple();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN,"");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.CORAL, 100);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.CORAL, 100);
    }


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive)
            createNewApple();

        if (!snake.isAlive)
            gameOver();

        if (SnakeGame.GOAL < snake.getLength())
            win();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
        }
    }
}
