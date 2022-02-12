package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName_1;
        String fileName_2;

        try {
            fileName_1 = reader.readLine();
            fileName_2 = reader.readLine();
            reader.close();
            reader = new BufferedReader(new FileReader(fileName_1));
            String s;
            while ((s = reader.readLine()) != null)
                allLines.add(s);
            reader.close();
            reader = new BufferedReader(new FileReader(fileName_2));
            while ((s = reader.readLine()) != null)
                forRemoveLines.add(s);
            reader.close();
            new Solution().joinData();
        } catch (Exception ignore) {
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines))
            allLines.removeAll(forRemoveLines);
        else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
