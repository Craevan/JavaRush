package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/

public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private Object writeObject(ObjectOutputStream out) throws NotSerializableException {
            throw new NotSerializableException();
        }

        private Object readObject(ObjectInputStream in) throws NotSerializableException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) {

    }
}
