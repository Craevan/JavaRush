package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class Ship extends GameObject {
    private List<int[][]> frames;

    private int frameIndex;

    private boolean loopAnimation = false;

    public boolean isAlive = true;

    public Ship(double x, double y) {
        super(x, y);
    }

    public void setStaticView(int[][] viewFrame) {
        super.setMatrix(viewFrame);
        frames = new ArrayList<>();
        frames.add(viewFrame);
        frameIndex = 0;
    }

    public Bullet fire() {
        return null;
    }

    public void kill() {
        isAlive = false;
    }

    public boolean isVisible() {
        return isAlive || frameIndex < frames.size();
    }

    public void setAnimatedView(boolean isLoopAnimation, int[][]... viewFrames) {
        this.loopAnimation = isLoopAnimation;
    }

    public void nextFrame() {
        frameIndex++;
        if (frameIndex >= frames.size() && !loopAnimation) {
            return;
        }
        if (frameIndex >= frames.size() && loopAnimation) {
            frameIndex = 0;
        }
        matrix = frames.get(frameIndex);
    }

    @Override
    public void draw(Game game) {
        super.draw(game);
        nextFrame();
    }
}
