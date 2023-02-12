package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;

    private Direction direction = Direction.RIGHT;

    private List<EnemyShip> ships;

    public EnemyFleet() {
        createShips();
    }

    public void draw(Game game) {
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    public void move() {
        if (ships.size() == 0) {
            return;
        }
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, getSpeed());
            }
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, getSpeed());
            }
        } else {
            for (EnemyShip ship : ships) {
                ship.move(direction, getSpeed());
            }
        }
    }

    public Bullet fire(Game game) {
        if (ships.size() == 0) {
            return null;
        }
        if (game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) > 0) {
            return null;
        }
        return ships.get(game.getRandomNumber(ships.size())).fire();
    }

    public void verifyHit(List<Bullet> bullets) {
        for (EnemyShip enemyShip : ships) {
            for (Bullet bullet : bullets) {
                if (enemyShip.isCollision(bullet)) {
                    if (enemyShip.isAlive && bullet.isAlive) {
                        enemyShip.kill();
                        bullet.kill();
                    }
                }
            }
        }
    }

    public void deleteHiddenShips() {
        ships.removeIf(enemyShip -> !enemyShip.isVisible());
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2.0 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2.0 - 1, 5));
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

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }
}
