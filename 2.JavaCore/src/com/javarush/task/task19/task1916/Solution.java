package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        List<String> stringList_1 = new ArrayList<>();
        List<String> stringList_2 = new ArrayList<>();

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
        if (stringList_1.size() >= stringList_2.size())
            lineCompare(stringList_1, stringList_2);
        else
            lineCompare(stringList_2, stringList_1);
        //
        for (LineItem line : lines) {
            System.out.println(line.type + " " + line.line);
        }
    }

    private static void lineCompare(List<String> arrayList_1, List<String> arrayList_2) {
        int index = 0;
        for (String s : arrayList_1) {
            for (int i = index; i < arrayList_2.size(); i++) {
                String s2 = arrayList_2.get(i);
                if (s2.equals(s)) {
                    lines.add(new LineItem(Type.SAME, s));
                    index = i + 1;
                    break;
                }
                else {
                    int next = i + 1;
                    if (next < arrayList_2.size()) {
                        String nextLine = arrayList_2.get(next);
                        if (!s.equals(nextLine)) {
                            lines.add(new LineItem(Type.REMOVED, s));
                            break;
                        }
                        else {
                            lines.add(new LineItem(Type.ADDED, s2));
//                            lines.add(new LineItem(Type.SAME, s));
                        }
                    }
                    else {
                        lines.add(new LineItem(Type.REMOVED, s));
                        break;
                    }
                }

            }
            if (index >= arrayList_2.size())
                lines.add(new LineItem(Type.REMOVED, s));
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
