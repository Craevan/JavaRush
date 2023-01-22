package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

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
        this.statisticStorage.put(data);
    }

    public Map<String, Long> getProfitFromVideoAd() {
        Map<String, Long> profitByDay = new HashMap<>();
        List<EventDataRow> eventList = statisticStorage.storage.get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        long total = 0L;
        for (EventDataRow event : eventList) {
            VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) event;
            total += videoEvent.getAmount();
            profitByDay.merge(sdf.format(videoEvent.getDate()), videoEvent.getAmount(), Long::sum);
        }
        profitByDay.put("Total", total);
        return profitByDay;
    }

    public Map<String, Map<String, Integer>> getCookWorkLoad() {
        Map<String, Map<String, Integer>> workLoad = new HashMap<>();
        List<EventDataRow> eventList = statisticStorage.storage.get(EventType.COOKED_ORDER);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        for (EventDataRow eventDataRow : eventList) {
            CookedOrderEventDataRow cookEvent = (CookedOrderEventDataRow) eventDataRow;
            String date = sdf.format(cookEvent.getDate());
            Map<String, Integer> cookMap = workLoad.get(date);
            if (cookMap == null) {
                cookMap = new HashMap<>();
            }
            cookMap.merge(cookEvent.getCookName(), cookEvent.getCookingTimeSeconds(), Integer::sum);
            workLoad.put(date, cookMap);
        }
        return workLoad;
    }

    private class StatisticStorage {

        private final Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                this.storage.put(type, new ArrayList<>());
            }
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data) {
            List<EventDataRow> eventDataRowList = storage.get(data.getType());
            eventDataRowList.add(data);
            storage.put(data.getType(), eventDataRowList);
        }
    }
}
