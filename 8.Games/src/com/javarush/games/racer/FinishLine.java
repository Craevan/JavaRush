package com.javarush.games.racer;

public class FinishLine extends GameObject {
    private boolean isVisible = false;

    public FinishLine() {
        super(RacerGame.ROADSIDE_WIDTH, -1 * ShapeMatrix.FINISH_LINE.length, ShapeMatrix.FINISH_LINE);
    }

    public void show() {
        isVisible = true;
    }

    public void move(final int boost) {
        if (!isVisible) {
            return;
        }
        y += boost;
    }

    public boolean isCrossed(final PlayerCar player) {
        return y > player.y;
    }
}
