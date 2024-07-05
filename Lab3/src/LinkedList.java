/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LinkedList {
    private Node head;
    private Node current;
    private Node previous;
    private Node tail;

    // Inner Node class
    private class Node {
        private String value;
        private Node link;

        public Node(String value, Node link) {
            this.value = value;
            this.link = link;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Default constructor
    public LinkedList() {
        head = current = previous = tail = null;
    }

    // toString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            result.append(temp.value);
            if (temp.link != null) {
                result.append(", ");
            }
            temp = temp.link;
        }
        return result.toString();
    }

    // Add element to the beginning of the linked list
    public void addFirst(String value) {
        Node newNode = new Node(value, head);
        head = newNode;
        if (tail == null) {
            tail = head;
        }
    }

    // Add element to the end of the linked list
    public void addLast(String value) {
        Node newNode = new Node(value, null);
        if (tail != null) {
            tail.link = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
    }

    // Add element after the current node
    public void addAfterCurrent(String value) {
        if (current != null) {
            Node newNode = new Node(value, current.link);
            current.link = newNode;
            if (current == tail) {
                tail = newNode;
            }
        }
    }

    // Return value of the current node
    public String getCurrentValue() {
        return current != null ? current.value : null;
    }

    // Move current node forward
    public void moveCurrentForward() {
        if (current != null) {
            previous = current;
            current = current.link;
        }
    }

    // Set current node to the head
    public void resetCurrentToHead() {
        previous = null;
        current = head;
    }

    // Remove the current node
    public void removeCurrent() {
        if (current != null) {
            if (current == head) {
                head = head.link;
                if (head == null) {
                    tail = null;
                }
            } else {
                previous.link = current.link;
                if (current == tail) {
                    tail = previous;
                }
            }
            current = current.link;
        }
    }

    // Find a value in the linked list
    public boolean find(String value) {
        Node temp = head;
        while (temp != null) {
            if (temp.value.equals(value)) {
                return true;
            }
            temp = temp.link;
        }
        return false;
    }

    // Read contents of a file into the linked list
    public void readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            addLast(line);
        }
        reader.close();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        try {
            list.readFromFile("lab3input.txt");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        while (true) {
            System.out.println("Current list: " + list);
            System.out.print("Enter a color: ");
            String input = scanner.nextLine();

            if (list.find(input)) {
                System.out.print("Would you like to remove this value from the list? ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    list.resetCurrentToHead();
                    while (list.current != null) {
                        if (list.getCurrentValue().equals(input)) {
                            list.removeCurrent();
                            break;
                        }
                        list.moveCurrentForward();
                    }
                }
            } else {
                System.out.print("Would you like to:\n"
                        + "1 - add " + input + " as the first value in the list\n"
                        + "2 - add " + input + " as the last value in the list\n"
                        + "3 - neither (don't add " + input + " to the list)\n"
                        + "Enter your choice: ");
                String response = scanner.nextLine();
                if (response.equals("1")) {
                    list.addFirst(input);
                } else if (response.equals("2")) {
                    list.addLast(input);
                }
            }

            System.out.print("Would you like to enter another color? ");
            String continueResponse = scanner.nextLine();
            if (continueResponse.equalsIgnoreCase("no")) {
                break;
            }
        }

        scanner.close();
    }
}
