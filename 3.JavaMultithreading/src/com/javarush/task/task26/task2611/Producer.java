package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {

    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            int key = 1;
            while (true) {
                map.put(String.valueOf(key), "Some text for " + key);
                key++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s thread was terminated%n", Thread.currentThread().getName());
        }
    }
}
