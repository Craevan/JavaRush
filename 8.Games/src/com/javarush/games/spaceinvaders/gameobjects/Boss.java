package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class Boss extends EnemyShip {
    private int frameCount = 0;

    public Boss(double x, double y) {
        super(x, y);
        this.score = 100;
        setAnimatedView(true, ShapeMatrix.BOSS_ANIMATION_FIRST, ShapeMatrix.BOSS_ANIMATION_SECOND);
    }

    @Override
    public void nextFrame() {
        ++frameCount;
        if (frameCount % 10 == 0 || !this.isAlive) {
            super.nextFrame();
        }
    }

    @Override
    public Bullet fire() {
        if (!this.isAlive) {
            return null;
        }
        return matrix == ShapeMatrix.BOSS_ANIMATION_FIRST ?
                new Bullet(x + 6, y + height, Direction.DOWN) :
                new Bullet(x, y + height, Direction.DOWN);
    }

    @Override
    public void kill() {
        if (!this.isAlive) {
            return;
        }
        isAlive = false;
        super.setAnimatedView(false,
                ShapeMatrix.KILL_BOSS_ANIMATION_FIRST,
                ShapeMatrix.KILL_BOSS_ANIMATION_SECOND,
                ShapeMatrix.KILL_BOSS_ANIMATION_THIRD
        );
    }
}
