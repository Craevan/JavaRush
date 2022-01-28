package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new FirstThread());
        threads.add(new SecondThread());
        threads.add(new ThirdThread());
        threads.add(new FourthThread());
        threads.add(new FifthThread());
    }

    public static void main(String[] args) {
    }

    public static class FirstThread extends Thread {
        @Override
        public void run() {
            while (true) {
                super.run();
            }
        }
    }

    public static class SecondThread extends Thread {
        @Override
        public void run() {
            try {
                throw new InterruptedException();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class ThirdThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class FourthThread extends Thread implements Message {
        @Override
        public void showWarning() {
            this.interrupt();
        }

        @Override
        public void run() {
            while (!this.isInterrupted()) {

            }
        }
    }

    public static class FifthThread extends Thread {
        private static final String END = "N";
        private int sum = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString;

        {
            try {
                inputString = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!inputString.equals(END)) {
                sum += Integer.parseInt(inputString);
                try {
                    inputString = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(sum);
        }
    }
}