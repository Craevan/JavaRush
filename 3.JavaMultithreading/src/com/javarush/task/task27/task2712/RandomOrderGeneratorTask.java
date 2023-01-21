package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private final List<Tablet> tablets;

    private final int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int randomTable = (int) (Math.random() * tablets.size());
                Tablet actual = tablets.get(randomTable);
                actual.createOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignore) {

        }
    }
}
