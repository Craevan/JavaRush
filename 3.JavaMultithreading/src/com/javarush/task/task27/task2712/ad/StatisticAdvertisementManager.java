package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return StatisticAdvertisementManagerHolder.INSTANCE_HOLDER;
    }

    private static class StatisticAdvertisementManagerHolder {
        private static final StatisticAdvertisementManager INSTANCE_HOLDER = new StatisticAdvertisementManager();
    }

    public List<Advertisement> getVideoSet(boolean isActive) {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (!isActive ^ advertisement.isActive()) {
                result.add(advertisement);
            }
        }
        return result;
    }
}
