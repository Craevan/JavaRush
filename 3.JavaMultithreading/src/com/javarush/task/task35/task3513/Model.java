package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 4;

    private Tile[][] gameTiles;
    int score = 0;
    int maxTile = 0;
    private Stack<Tile[][]> previousStates;
    private Stack<Integer> previousScores;
    private boolean isSaveNeeded = true;


    public Model() {
        previousStates = new Stack<>();
        previousScores = new Stack<>();
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        boolean canBeMerged;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            canBeMerged = mergeTiles(gameTiles[i]);
            if (canBeMerged)
                return true;
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) {
                    return true;
                }
            }
        }
        rotate();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            canBeMerged = mergeTiles(gameTiles[i]);
            if (canBeMerged)
                return true;
        }
        return false;
    }

    public void left() {
        if (isSaveNeeded)
            saveState(gameTiles);
        isSaveNeeded = true;
        boolean moved = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                moved = true;
        }
        if (moved)
            addTile();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[j][i] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        priorityQueue.peek().getMove().move();
    }

    private boolean hasBoardChanged() {
        Tile[][] tiles = previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (tiles[i][j].value != gameTiles[i][j].value)
                    return true;
            }
        }
        return false;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency = new MoveEfficiency(-1, 0, move);
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTilesCount(), score, move);
        }
        rollback();
        return moveEfficiency;
    }

    private int getEmptyTilesCount() {
        return getEmptyTiles().size();
    }

    private void saveState(Tile[][] state) {
        Tile[][] currentState = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                currentState[i][j] = new Tile(state[i][j].value);
            }
        }
        previousStates.push(currentState);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    private void rotate() {
        Tile[][] resultArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                resultArray[j][gameTiles.length - i - 1] = gameTiles[i][j];
            }
        }
        gameTiles = resultArray;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty())
                    emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }

    private void addTile() {
        if (getEmptyTiles().isEmpty())
            return;
        List<Tile> emptyTiles = getEmptyTiles();
        Tile rndTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        rndTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean moved = false;
        int position = 0;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value > 0) {
                if (i != position) {
                    tiles[position].value = tiles[i].value;
                    tiles[i] = new Tile();
                    moved = true;
                }
                ++position;
            }
        }
        return moved;
    }

    private boolean mergeTiles(Tile[] tiles) {
        Tile current;
        Tile next;
        boolean merged = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].isEmpty())
                continue;
            current = tiles[i];
            if (i + 1 < tiles.length) {
                next = tiles[i + 1];
                if (current != null && current.value == next.value) {
                    if (current.value * 2 > maxTile)
                        maxTile = current.value * 2;
                    score += current.value * 2;
                    tiles[i].value = current.value * 2;
                    tiles[i + 1].value = 0;
                    merged = true;
                    compressTiles(tiles);
                }
            }
        }
        return merged;
    }

}
