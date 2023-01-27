package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long start = new Date().getTime();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        long end = new Date().getTime();
        return end - start;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long start = new Date().getTime();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        long end = new Date().getTime();
        return end - start;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
            ids.add((long) (i + 1));
        }

        long hmssTimeIds = getTimeToGetIds(shortener1, origStrings, new HashSet<>());
        long hbmssTimeIds = getTimeToGetIds(shortener2, origStrings, new HashSet<>());

        boolean actual = hmssTimeIds > hbmssTimeIds;
        Assert.assertTrue(actual);

        long hmssTimeValues = getTimeToGetStrings(shortener1, ids, new HashSet<>());
        long hbmssTimeValues = getTimeToGetStrings(shortener2, ids, new HashSet<>());
        Assert.assertEquals(hmssTimeValues, hbmssTimeValues, 30);
    }
}
