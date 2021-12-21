package com.javarush.task.pro.task15.task1514;

import java.nio.file.Path;
import java.util.Scanner;

/* 
Все относительно
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        //напишите тут ваш код
        Path pathFirst = Path.of(str1);
        Path pathSecond = Path.of(str2);
        try {
            System.out.println(pathFirst.relativize(pathSecond));
        } catch (Exception e) {

        }
        finally {
            scanner.close();
        }
    }
}

