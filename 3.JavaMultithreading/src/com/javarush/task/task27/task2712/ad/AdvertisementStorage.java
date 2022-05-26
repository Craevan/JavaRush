package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {

    private final List<Advertisement> videos = new ArrayList<>();

    private static class AdvertisementStorageHolder {
        public static final AdvertisementStorage STORAGE_INSTANCE = new AdvertisementStorage();
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        new Advertisement(someContent, "Second Video", 100, 10, 15 * 60);
        new Advertisement(someContent, "Third Video", 400, 2, 10 * 60);
    }

    public static AdvertisementStorage getInstance() {
        return AdvertisementStorageHolder.STORAGE_INSTANCE;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
