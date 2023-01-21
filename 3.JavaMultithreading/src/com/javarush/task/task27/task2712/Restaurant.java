package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws InterruptedException {

        Cook cook_1 = new Cook("first");
        Cook cook_2 = new Cook("second");
        StatisticManager.getInstance().register(cook_1);
        StatisticManager.getInstance().register(cook_2);
        List<Tablet> tablets = new ArrayList<>();
        Waiter waiter = new Waiter();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i));
            tablets.get(i).addObserver(cook_1);
            tablets.get(i).addObserver(cook_2);
        }
        cook_1.addObserver(waiter);
        cook_2.addObserver(waiter);
        Thread randomOrder = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        randomOrder.start();
        Thread.sleep(1000);
        randomOrder.interrupt();





//        Tablet tablet = new Tablet(5);
//        Cook cook = new Cook("Amigo");
//        Waiter waiter = new Waiter();
//        tablet.addObserver(cook);
//        cook.addObserver(waiter);

//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();

//        DirectorTablet directorTablet = new DirectorTablet();
//        directorTablet.printAdvertisementProfit();
//        directorTablet.printCookWorkloading();
//        directorTablet.printActiveVideoSet();
//        directorTablet.printArchivedVideoSet();
    }
}
