package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {

    private final String name;

    private LinkedBlockingQueue<Order> queue;

    private boolean busy;

    public Cook(final String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void startCookingOrder(Order order) throws InterruptedException {
        busy = true;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(
                order.getTablet().toString(),
                this.name,
                order.getTotalCookingTime() * 60,
                order.getDishes()
        ));
        ConsoleHelper.writeMessage("Start cooking - " + order);
        setChanged();
        notifyObservers(order);
        Thread.sleep(order.getTotalCookingTime() * 10);
        busy = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10);
                if (!queue.isEmpty()) {
                    if (!this.isBusy()) {
                        this.startCookingOrder(queue.take());
                    }
                }
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
