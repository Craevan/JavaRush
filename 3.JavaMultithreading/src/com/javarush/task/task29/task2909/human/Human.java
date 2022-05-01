package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    private final List<Human> children = new ArrayList<>();

//    protected int[] size;
    protected Size size;

//    public static final int FIRST = 1;
//    public static final int SECOND = 2;
//    public static final int THIRD = 3;
//    public static final int FOURTH = 4;
//    private int bloodGroup;
    private BloodGroup bloodGroup;

//    public void setBloodGroup(int code) {
//        bloodGroup = code;
//    }
//
//    public int getBloodGroup() {
//        return bloodGroup;
//    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Human(String name, int age) {
        this.id = nextId;
        nextId++;
        this.age = age;
        this.name = name;
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(Human human) {
        children.add(human);
    }
    public void removeChild(Human human) {
        children.remove(human);
    }

    @Override
    public void live() {

    }

//    public void fight() {
//    }

    public String getPosition() {
        return "Человек";
    }

    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public class Size {

        public int height;
        public int weight;

    }

}