package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable {

    private final String name;

    public Cook(final String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) {
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(
                order.getTablet().toString(),
                this.name,
                order.getTotalCookingTime() * 60,
                order.getDishes()
        ));
        ConsoleHelper.writeMessage("Start cooking - " + order);
        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
