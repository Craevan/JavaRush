package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
//        testStrategy(new FileStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        strings.stream().iterator().forEachRemaining(s ->
                result.add(shortener.getId(s)));
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        keys.stream().iterator().forEachRemaining(id ->
                result.add(shortener.getString(id)));
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testStringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testStringSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startId = new Date();
        Set<Long> resultId = getIds(shortener, testStringSet);
        Date endId = new Date();
        Helper.printMessage(String.valueOf(endId.getTime() - startId.getTime()));

        Set<Long> testIds = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testIds.add((long) i);
        }
        Date startStrings = new Date();
        Set<String> resultStringSet = getStrings(shortener, testIds);
        Date endStrings = new Date();
        Helper.printMessage(String.valueOf(endStrings.getTime() - startStrings.getTime()));
        if (resultId.size() == testStringSet.size() &&
                resultStringSet.size() == testIds.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
