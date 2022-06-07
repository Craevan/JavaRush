package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.*;

public class StatisticManager {

    private final StatisticStorage statisticStorage = new StatisticStorage();
    private final Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    private static class StatisticManagerHolder {
        public static final StatisticManager HOLDER_INSTANCE = new StatisticManager();
    }

    public static StatisticManager getInstance() {
        return StatisticManagerHolder.HOLDER_INSTANCE;
    }

    public void register(EventDataRow data) {
        this.statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    private class StatisticStorage {

        private final Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            List<EventDataRow> eventDataRowList = storage.get(data.getType());
            eventDataRowList.add(data);
            storage.put(data.getType(), eventDataRowList);
        }
    }
}
