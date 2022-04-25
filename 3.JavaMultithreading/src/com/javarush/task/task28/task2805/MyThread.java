package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static final AtomicInteger priority = new AtomicInteger(1);

    private synchronized void setCustomPriority() {
        int threadPriority = priority.getAndIncrement();
        if (getThreadGroup() != null && threadPriority > getThreadGroup().getMaxPriority()) {
            threadPriority = getThreadGroup().getMaxPriority();
        }

        if (priority.intValue() > MAX_PRIORITY)
            priority.set(1);

        setPriority(threadPriority);
    }

    public MyThread() {
        setCustomPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setCustomPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setCustomPriority();
    }

    public MyThread(String name) {
        super(name);
        setCustomPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setCustomPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCustomPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setCustomPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setCustomPriority();
    }

}
