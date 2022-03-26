package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {

    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\u2691";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score = 0;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
//        isGameStopped = false;
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private void countMineNeighbors() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (!gameField[j][i].isMine) {
                    for (GameObject go : getNeighbors(gameField[j][i])) {
                        if (go.isMine)
                            ++gameField[j][i].countMineNeighbors;
                    }
                }
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void openTile(int x, int y) {
        GameObject go = gameField[y][x];
        if (go.isOpen || go.isFlag || isGameStopped) {
            return;
        }
        go.isOpen = true;
        --countClosedTiles;
        if (!go.isMine) {
            score += 5;
            setScore(score);
            if (countClosedTiles == countMinesOnField)
                win();

        }
        setCellColor(x, y, Color.GREEN);

        if (go.isMine) {
            setCellValue(x, y, MINE);
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        }
        else if (go.countMineNeighbors == 0) {
            setCellValue(x, y, "");
            for (GameObject tmp : getNeighbors(go)) {
                if (!tmp.isOpen)
                    openTile(tmp.x, tmp.y);
            }
        }
        else
            setCellNumber(x, y, go.countMineNeighbors);
    }

    private void markTile(int x, int y) {
        if (isGameStopped)
            return;
        GameObject go = gameField[y][x];
        if (!go.isOpen && countFlags != 0) {
            if (!go.isFlag) {
                go.isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
            }
            else {
                go.isFlag = false;
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
            }
        }

    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "!!!WIN!!!", Color.BLUE, 100);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "Game Over", Color.BLUE, 100);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();

    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
            return;
        }
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }
}