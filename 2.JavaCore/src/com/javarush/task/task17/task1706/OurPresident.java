package com.javarush.task.task17.task1706;

public class OurPresident {

    private static final OurPresident president;

    static {
        synchronized (OurPresident.class) {
            president = new OurPresident();
        }
    }

    public static OurPresident getOurPresident() {
        return OurPresident.president;
    }

    private OurPresident() {
    }
}
