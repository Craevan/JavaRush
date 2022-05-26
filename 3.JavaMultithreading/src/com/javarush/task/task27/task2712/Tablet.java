package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {

    static Logger logger = Logger.getLogger(Tablet.class.getName());

    private final int number;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order order;
        AdvertisementManager adManager;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if (order.isEmpty())
                return null;
            setChanged();
            notifyObservers(order);
            adManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            adManager.processVideos();
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
