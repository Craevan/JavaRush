package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/

import static java.lang.Thread.*;

public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread t : threads) {
            State current = t.getState();
            switch (current) {
                case NEW:
                    t.start();
                    break;
                case RUNNABLE:
                    t.isInterrupted();
                    break;
                case WAITING:
                case TIMED_WAITING:
                case BLOCKED:
                    t.interrupt();
                    break;
                case TERMINATED:
                    System.out.println(t.getPriority());
            }
        }
    }

    public static void main(String[] args) {
    }
}
