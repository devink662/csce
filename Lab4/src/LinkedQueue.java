/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */
public class LinkedQueue<T> {

    // Inner Node class
    private class Node {
        private T value;
        private Node next;

        // Constructor
        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;

    // Default constructor
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
    }

    // Method to add an element to the end of the queue
    public void enqueue(T value) {
        Node newNode = new Node(value, null);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Method to remove an element from the front of the queue
    public T dequeue() {
        if (front == null) {
            return null;
        }
        T value = front.value;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return value;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Method to return the size of the queue
    public int size() {
        int size = 0;
        Node temp = front;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    // toString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = front;
        while (temp != null) {
            result.append(temp.value);
            if (temp.next != null) {
                result.append("\n");
            }
            temp = temp.next;
        }
        return result.toString();
    }
}
