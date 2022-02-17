package com.javarush.task.task19.task1923;

import java.io.*;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) {
       try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
           while (reader.ready()) {
               String[] words = reader.readLine().split(" ");
               for (String s : words) {
                   char[] chars = s.toCharArray();
                   for (char c : chars) {
                       if (Character.isDigit(c)) {
                           writer.write(s);
                           writer.write(" ");
                           break;
                       }
                   }

               }
           }
       } catch (IOException e) {
            e.printStackTrace();
       }
    }
}
