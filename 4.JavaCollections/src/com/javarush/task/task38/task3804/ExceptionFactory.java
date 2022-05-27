package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable createException(Enum e) {
        if (e == null) {
            return new IllegalArgumentException();
        }
        String name = e.name().toLowerCase().replaceAll("_", " ");
        String firstCapitalLetter = name.substring(0, 1).toUpperCase();
        String message = firstCapitalLetter + name.substring(1);

        switch (e.getClass().getSimpleName()) {
            case ("ApplicationExceptionMessage") :
                return new Exception(message);
            case ("DatabaseExceptionMessage") :
                return new RuntimeException(message);
            case ("UserExceptionMessage") :
                return new Error(message);
            default:
                return new IllegalArgumentException();
        }
    }
}
