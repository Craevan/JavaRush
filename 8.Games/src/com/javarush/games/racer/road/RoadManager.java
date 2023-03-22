package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    private static final int PLAYER_CAR_DISTANCE = 12;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;

    private final List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    public void draw(final Game game) {
        items.forEach(item -> item.draw(game));
    }

    public void move(final int boost) {
        items.forEach(item -> item.move(boost + item.speed, items));
        deletePassedItems();
    }

    public void generateNewRoadObjects(final Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    public boolean checkCrush(final PlayerCar player) {
        return items.stream().anyMatch(item -> item.isCollision(player));
    }

    private RoadObject createRoadObject(final RoadObjectType type, final int x, final int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        } else if (type == RoadObjectType.DRUNK_CAR) {
            return new MovingCar(x, y);
        } else {
            return new Car(type, x, y);
        }
    }

    private void addRoadObject(final RoadObjectType type, final Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject roadObject = createRoadObject(type, x, y);
        if (isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }

    private boolean isThornExists() {
        return items
                .stream()
                .anyMatch(item -> item.type == RoadObjectType.THORN);
    }

    private boolean isMovingCarExists() {
        return items
                .stream()
                .anyMatch(item -> item.type == RoadObjectType.DRUNK_CAR);
    }

    private void generateThorn(final Game game) {
        if (game.getRandomNumber(100) < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateMovingCar(final Game game) {
        if (game.getRandomNumber(100) < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    private void generateRegularCar(final Game game) {
        int carTypeNumber = game.getRandomNumber(4);
        if (game.getRandomNumber(100) < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private void deletePassedItems() {
        items.removeIf(roadObject -> {
            boolean removed = roadObject.y >= RacerGame.HEIGHT;
            if (removed && roadObject.type != RoadObjectType.THORN) {
                passedCarsCount++;
            }
            return removed;
        });
    }

    private boolean isRoadSpaceFree(final RoadObject object) {
        return items.stream()
                .noneMatch(item -> item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE));
    }
}
