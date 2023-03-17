package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;

    private final List<RoadObject> items = new ArrayList<>();

    public void draw(final Game game) {
        items.forEach(item -> item.draw(game));
    }

    public void move(final int boost) {
        items.forEach(item -> item.move(boost + item.speed));
        deletePassedItems();
    }

    public void generateNewRoadObjects(final Game game) {
        generateThorn(game);
    }

    public boolean checkCrush(final PlayerCar player) {
        return items.stream().anyMatch(item -> item.isCollision(player));
    }

    private RoadObject createRoadObject(final RoadObjectType type, final int x, final int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        }
        return null;
    }

    private void addRoadObject(final RoadObjectType type, final Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject roadObject = createRoadObject(type, x, y);
        if (roadObject != null) {
            items.add(roadObject);
        }
    }

    private boolean isThornExists() {
        return items
                .stream()
                .anyMatch(item -> item.type == RoadObjectType.THORN);
    }

    private void generateThorn(final Game game) {
        if (game.getRandomNumber(100) < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void deletePassedItems() {
        items.removeIf(roadObject -> roadObject.y >= RacerGame.HEIGHT);
    }
}
