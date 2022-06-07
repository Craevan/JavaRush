package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {

    private final StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    private static class StatisticManagerHolder {
        public static final StatisticManager HOLDER_INSTANCE = new StatisticManager();
    }

    public static StatisticManager getInstance() {
        return StatisticManagerHolder.HOLDER_INSTANCE;
    }

    public void register(EventDataRow data) {
        
    }

    private class StatisticStorage {
        private final Map<EventType, List<EventDataRow>> storage = new HashMap<>();
    }
}
