package com.javarush.task.task27.task2712.ad;

public class AdvertisementStorage {

    private static class AdvertisementStorageHolder {
        public static final AdvertisementStorage STORAGE_INSTANCE = new AdvertisementStorage();
    }

    public static AdvertisementStorage getInstance() {
        return AdvertisementStorageHolder.STORAGE_INSTANCE;
    }

    private AdvertisementStorage() {
    }
}
