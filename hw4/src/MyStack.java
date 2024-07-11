/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 * Homework Assignment 4
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 *
 * This class implements a stack data structure using a singly linked list.
 */

public class MyStack<T> {
    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node top;

    public MyStack() {
        top = null;
    }

    public void push(T value) {
        top = new Node(value, top);
    }

    public T pop() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        T value = top.value;
        top = top.next;
        return value;
    }

    public T peek() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
