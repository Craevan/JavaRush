package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    private final List<Entry<String>> entryList = new ArrayList<>();

    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("root");
        entryList.add(root);
    }

    public void remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }

        for (Entry<String> stringEntry : entryList) {
            if ()
        }
    }

    @Override
    public int size() {
        return entryList.size() - 1;
    }

    public String getParent(String s) {
        for (Entry<String> stringEntry : entryList) {
            if (stringEntry.elementName.equals(s)) {
                return stringEntry.parent.elementName;
            }
        }

        return null;
    }

    @Override
    public boolean add(String s) {
        boolean added = false;
        for (Entry<String> stringEntry : entryList) {
            if (stringEntry.isAvailableToAddChildren()) {
                if (stringEntry.availableToAddLeftChildren) {
                    stringEntry.leftChild = new Entry<>(s);
                    stringEntry.leftChild.parent = stringEntry;
                    entryList.add(stringEntry.leftChild);
                    added = true;
                    stringEntry.availableToAddLeftChildren = false;
                    break;
                }
                else {
                    stringEntry.rightChild = new Entry<>(s);
                    stringEntry.rightChild.parent = stringEntry;
                    entryList.add(stringEntry.rightChild);
                    added = true;
                    stringEntry.availableToAddRightChildren = false;
                    break;
                }
            }
        }

        return added;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public  Entry(String elementName) {
            this.elementName = elementName;
            availableToAddRightChildren = true;
            availableToAddLeftChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

    }

}
