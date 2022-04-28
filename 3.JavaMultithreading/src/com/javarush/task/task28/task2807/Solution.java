package com.javarush.task.task28.task2807;

import java.util.concurrent.*;

/* 
Знакомство с ThreadPoolExecutor
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        // Add your code here

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            queue.add(new Runnable() {
                int number = finalI + 1;
                @Override
                public void run() {
                    doExpensiveOperation(number);
                }
            });
        }

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, queue);
        tpe.prestartAllCoreThreads();
        tpe.shutdown();
        tpe.awaitTermination(5, TimeUnit.SECONDS);


        /* Example output
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
