/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.util.EmptyStackException;

public class ArrayStack<T> {
    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 10;

    // Default constructor
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor with specified capacity
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        stack = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }

    // Method to push an element onto the stack
    public void push(T newEntry) {
        if (topIndex == stack.length - 1) {
            doubleCapacity();
        }
        stack[++topIndex] = newEntry;
    }

    // Method to pop an element from the stack
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T top = stack[topIndex];
            stack[topIndex--] = null;
            return top;
        }
    }

    // Method to peek at the top element of the stack
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return topIndex < 0;
    }

    // Method to double the capacity of the stack array
    @SuppressWarnings("unchecked")
    private void doubleCapacity() {
        T[] oldStack = stack;
        int oldSize = oldStack.length;

        stack = (T[]) new Object[2 * oldSize];
        System.arraycopy(oldStack, 0, stack, 0, oldSize);
    }

    // Method to clear the stack
    @SuppressWarnings("unchecked")
    public void clear() {
        stack = (T[]) new Object[DEFAULT_CAPACITY];
        topIndex = -1;
    }

    // toString method to print the stack
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= topIndex; i++) {
            sb.append(stack[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
