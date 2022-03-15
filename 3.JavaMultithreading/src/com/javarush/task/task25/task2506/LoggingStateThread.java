package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {

    private Thread targetThread;

    public LoggingStateThread(Thread thread) {
        this.targetThread = thread;
    }

    @Override
    public void run() {
        State state = targetThread.getState();
        State currentState;
        System.out.println(state);
        while (true) {
            if (state != (currentState = targetThread.getState())) {
                state = currentState;
                System.out.println(state);
            }

            if (targetThread.getState() == State.TERMINATED) {
                break;
            }
        }
        this.interrupt();

    }
}
