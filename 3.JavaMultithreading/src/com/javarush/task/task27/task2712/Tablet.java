package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

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

    public void createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            commonLogic(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    public void createTestOrder() {
        TestOrder order = null;
        try {
            order = new TestOrder(this);
            commonLogic(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    private boolean commonLogic(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (order.isEmpty()) {
            return true;
        }

        setChanged();
        notifyObservers(order);

        new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
        return false;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
