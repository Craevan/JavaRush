package com.javarush.task.task29.task2912;

public class ConsoleLogger extends AbstractLogger implements Logger {


    public ConsoleLogger(int level) {
        super(level);
    }

    @Override
    public void info(String message) {
        System.out.println("Logging to console: " + message);
    }
}