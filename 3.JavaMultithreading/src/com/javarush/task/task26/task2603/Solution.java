package com.javarush.task.task26.task2603;

import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>  {
        private final Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T t, T t1) {
            int result;
            for (Comparator<T> comp : comparators) {
                result = comp.compare(t, t1);
                if (result != 0)
                    return result;
            }

            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
