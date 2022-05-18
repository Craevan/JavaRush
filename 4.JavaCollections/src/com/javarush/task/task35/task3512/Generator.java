package com.javarush.task.task35.task3512;

public class Generator<T> {

    private final Class<T> clazz;

    public Generator(Class<T> clazz) {
        this.clazz = clazz;
    }

    T newInstance() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
