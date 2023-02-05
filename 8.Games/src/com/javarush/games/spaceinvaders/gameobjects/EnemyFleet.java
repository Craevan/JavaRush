package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.ShapeMatrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;

    private List<EnemyShip> ships;

    public EnemyFleet() {
        createShips();
    }

    public void draw(Game game) {
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
    }

    private double getLeftBorder() {
        return ships.stream()
                .map(enemyShip -> enemyShip.x)
                .min(Comparator.comparingDouble(x -> x))
                .get();
    }

    private double getRightBorder() {
        double rigth = 0;
        for (EnemyShip ship : ships) {
            double value = ship.x + ship.width;
            if (value > rigth) {
                rigth = value;
            }
        }
        return rigth;
    }
}
