package com.javarush.task.pro.task15.task1506;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/* 
Фейсконтроль
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String path = scanner.nextLine();
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String s : lines) {
                String result = s.replaceAll("[., ]", "");
                if (!result.isEmpty())
                    System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

