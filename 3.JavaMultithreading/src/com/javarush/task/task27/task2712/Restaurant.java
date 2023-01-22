package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;

    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>(200);

    public static void main(String[] args) throws InterruptedException {

        Cook cook_1 = new Cook("first");
        cook_1.setQueue(ORDER_QUEUE);

        Cook cook_2 = new Cook("second");
        cook_2.setQueue(ORDER_QUEUE);

        Waiter waiter = new Waiter();
        cook_1.addObserver(waiter);
        cook_2.addObserver(waiter);

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i));
            tablets.get(i).setQueue(ORDER_QUEUE);
        }

        Thread cook1Thread = new Thread(cook_1);
        Thread cook2Thread = new Thread(cook_2);

        cook1Thread.start();
        cook2Thread.start();

        Thread randomOrder = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        randomOrder.start();

        Thread.sleep(1000);
        randomOrder.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
