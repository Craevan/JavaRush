package com.javarush.task.task18.task1815;

import java.util.List;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements TableInterface {
        TableInterface tInt;

        public TableInterfaceWrapper(TableInterface tableInterface) {
            this.tInt = tableInterface;
        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            tInt.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return tInt.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            tInt.setHeaderText(newHeaderText);
        }
    }

    public interface TableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}