package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    static Hippodrome game;

    public static void main(String[] args) {
        Horse horse_1 = new Horse("1", 3, 0);
        Horse horse_2 = new Horse("2", 3, 0);
        Horse horse_3 = new Horse("3", 3, 0);
        ArrayList<Horse> listHorses = new ArrayList<>();
        listHorses.add(horse_1);
        listHorses.add(horse_2);
        listHorses.add(horse_3);
        game = new Hippodrome(listHorses);
        game.run();
        game.printWinner();
    }

    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    void move() {
        for (Horse horse : horses) {
            horse.move();
        }

    }

    void print() {
        for (Horse horse : horses) {
            horse.print();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    void run() {
        for (int i = 1; i < 101; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse horse : horses) {
            if (horse.getDistance() > winner.getDistance())
                winner = horse;
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
    
}
