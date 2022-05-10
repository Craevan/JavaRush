package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        int a = i % 2;
        int b = i / 2;
        String res = String.valueOf(a);
        if (b > 0) {
            RecursiveTask<String> rec = new BinaryRepresentationTask(b);
            rec.fork();
            return rec.join() + res;
        }
        return res;
    }
}
