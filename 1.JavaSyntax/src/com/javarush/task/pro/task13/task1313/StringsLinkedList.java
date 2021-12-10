package com.javarush.task.pro.task13.task1313;

public class StringsLinkedList {
    private Node first = new Node();
    private Node last = new Node();

    public void printAll() {
        Node currentElement = first.next;
        while ((currentElement) != null) {
            System.out.println(currentElement.value);
            currentElement = currentElement.next;
        }
    }

    public void add(String value) {
        //напишите тут ваш код
        Node innerNode = new Node();
        if (first.next == null) {
            innerNode.value = value;
            first.next = innerNode;
            last.prev = innerNode;

        }
        else {
            innerNode.prev = last.prev;
            innerNode.value = value;
            last.prev.next = innerNode;
            last.prev = innerNode;


        }
        innerNode.value = value;
    }

    public static class Node {
        private Node prev;
        private String value;
        private Node next;
    }
}
