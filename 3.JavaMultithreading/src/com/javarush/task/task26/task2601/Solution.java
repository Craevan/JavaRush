package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        int mediana = getMediana(array);

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int first = o1 - mediana;
                int second = o2 - mediana;

                return (int) ((Math.pow(first, 2) - Math.pow(second, 2)) * 100);
            }
        });
        return array;
    }

    private static int getMediana(Integer[] array) {
        Arrays.sort(array);
        int mediana;

        if (array.length % 2 == 1) {
            mediana = array[array.length / 2];
        } else {
            mediana = (array[array.length / 2 - 1] + array[array.length / 2]) / 2;
        }
        return mediana;
    }

}
