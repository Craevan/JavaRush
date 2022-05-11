package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {

    private static final int SIDE = 4;
    private boolean isGameStopped = false;
    private int maxValue = -1;
    private int score = 0;
    private int[][] gameField = new int[SIDE][SIDE];

    private int getMaxTileValue() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] > maxValue)
                    maxValue = gameField[i][j];
            }
        }
        return maxValue;
    }

    private boolean canUserMove() {
        boolean canBeMerged;
        for (int i = 0; i < SIDE; i++) {
            canBeMerged = mergeRow(gameField[i]);
            if (canBeMerged)
                return true;
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] == 0) {
                    return true;
                }
            }
        }
        rotateClockwise();
        for (int i = 0; i < SIDE; i++) {
            canBeMerged = mergeRow(gameField[i]);
            if (canBeMerged)
                return true;
        }
        return false;
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                setCellColoredNumber(i, j, gameField[j][i]);
            }
        }
    }

    private void createNewNumber() {
        if (2048 == getMaxTileValue()) {
            win();
        }
        int x;
        int y;
        boolean isOccupied = true;
        do {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
            if (gameField[y][x] == 0) {
                isOccupied = false;
                gameField[y][x] = getRandomNumber(10) < 9 ? 2 : 4;
            }
        }
        while (isOccupied);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "YOU WIN!!!", Color.CORAL, 32);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "YOU LOSE!!!", Color.CORAL, 32);
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case (2):
                return Color.AQUA;
            case (4):
                return Color.ALICEBLUE;
            case (8):
                return Color.AQUAMARINE;
            case (16):
                return Color.AZURE;
            case (32):
                return Color.BEIGE;
            case (64):
                return Color.BISQUE;
            case (128):
                return Color.BLANCHEDALMOND;
            case (256):
                return Color.BURLYWOOD;
            case (512):
                return Color.BLUE;
            case (1024):
                return Color.CORAL;
            case (2048):
                return Color.DARKCYAN;
            default:
                return Color.CRIMSON;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {

        if (value > 0)
            setCellValueEx(x, y, getColorByValue(value), String.valueOf(value));
        else
            setCellValueEx(x, y, getColorByValue(value), "");
    }

    private boolean compressRow(int[] row) {
        boolean moved = false;
        int position = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] > 0) {
                if (i != position) {
                    row[position] = row[i];
                    row[i] = 0;
                    moved = true;
                }
                ++position;
            }

        }

        return moved;
    }

    private boolean mergeRow(int[] row) {
        int current;
        int next;
        boolean merged = false;
        for (int i = 0; i < row.length; i++) {
            current = row[i];
            if (i + 1 <= row.length - 1) {
                next = row[i + 1];
                if (current != 0 && current == next) {
                    row[i] = current + next;
                    score += row[i];
                    setScore(score);
                    row[i + 1] = 0;
                    merged = true;
                }
            }

        }
        return merged;
    }

    private void moveLeft() {
        boolean moved = false;
        for (int i = 0; i < gameField.length; i++) {
            if (compressRow(gameField[i]) | mergeRow(gameField[i]) | compressRow(gameField[i]))
                moved = true;
        }
        if (moved)
            createNewNumber();

    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] resultArray = new int[SIDE][SIDE];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                resultArray[j][gameField.length - i - 1] = gameField[i][j];
            }
        }
        gameField = resultArray;
    }

    @Override
    public void initialize() {
//        super.initialize();
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                score = 0;
                setScore(score);
                createGame();
                drawScene();
            }
            else return;
        }
        if (!canUserMove()) {
            gameOver();
            return;
        }
        switch (key) {
            case LEFT:
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                moveRight();
                drawScene();
                break;
            case UP:
                moveUp();
                drawScene();
                break;
            case DOWN:
                moveDown();
                drawScene();
                break;
        }
    }
}
