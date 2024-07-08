
/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

public class LinkedList<T> {

    // Inner Node class
    private class Node {
        private T value;
        private Node next;

        // Constructor
        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private Node head;
    private Node current;
    private Node previous;
    private Node tail;

    // Default constructor
    public LinkedList() {
        this.head = null;
        this.current = null;
        this.previous = null;
        this.tail = null;
    }

    // toString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            result.append(temp.value);
            if (temp.next != null) {
                result.append("\n");
            }
            temp = temp.next;
        }
        return result.toString();
    }

    // Method to add an element to the beginning of the linked list
    public void addFirst(T value) {
        Node newNode = new Node(value, head);
        head = newNode;
        if (tail == null) {
            tail = head;
        }
    }

    // Method to add an element to the end of the linked list
    public void addLast(T value) {
        Node newNode = new Node(value, null);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Method that returns the value held in the current node
    public T getCurrentValue() {
        return (current != null) ? current.value : null;
    }

    // Method to move the current node forward (towards the end)
    public void moveCurrentForward() {
        if (current != null) {
            previous = current;
            current = current.next;
        }
    }

    // Method that sets the current node to the head
    public void resetCurrentToHead() {
        previous = null;
        current = head;
    }

    // Method that removes the current node from the linked list
    public void removeCurrent() {
        if (current != null) {
            if (current == head) {
                head = head.next;
                if (current == tail) {
                    tail = null;
                }
            } else {
                previous.next = current.next;
                if (current == tail) {
                    tail = previous;
                }
            }
            current = current.next;
        }
    }

    // Find method that searches the linked list for a specified value
    public boolean find(T value) {
        resetCurrentToHead();
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            moveCurrentForward();
        }
        return false;
    }
}
