package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        ArrayList<String> stringList_1 = new ArrayList<>();
        ArrayList<String> stringList_2 = new ArrayList<>();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName_1 = consoleReader.readLine();
            String fileName_2 = consoleReader.readLine();

            try (BufferedReader fileReader_1 = new BufferedReader(new FileReader(fileName_1));
                 BufferedReader fileReader_2 = new BufferedReader(new FileReader(fileName_2))) {

                while (fileReader_1.ready()) {
                    stringList_1.add(fileReader_1.readLine());
                }

                while (fileReader_2.ready()) {
                    stringList_2.add(fileReader_2.readLine());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        lineCompare(stringList_1, stringList_2);

        for (LineItem line : lines) {
            System.out.println(line.type + " " + line.line);
        }
    }

    private static void lineCompare(ArrayList<String> list_1, ArrayList<String> list_2) {
        int index = 0;
        int nextIndex = index + 1;
        String current = null;
        String next = null;
        for (String s1 : list_1) {
            if (index < list_2.size())
                current = list_2.get(index);
            if (nextIndex < list_2.size())
                next = list_2.get(nextIndex);
            if (s1.equals(current)) {
                lines.add(new LineItem(Type.SAME, s1));
                index++;
                nextIndex++;
            }
            else if (s1.equals(next)) {
                lines.add(new LineItem(Type.ADDED, current));
                lines.add(new LineItem(Type.SAME, next));
                index = index + 2;
                nextIndex = nextIndex + 2;
            }
            else {
                lines.add(new LineItem(Type.REMOVED, s1));
            }
        }
        if (index < list_2.size()) {
            for (int i = index; i < list_2.size(); i++) {
                lines.add(new LineItem(Type.ADDED, list_2.get(i)));

            }
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
