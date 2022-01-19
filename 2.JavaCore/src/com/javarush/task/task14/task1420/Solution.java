package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int nod = 1;
        int max = 0;
        for (int i = 1; i <= Math.min(a, b) ; i++) {
            if (a % i == 0 && b % i == 0) {
                max = i;
            }
        }
        System.out.println(max);
    }
}
