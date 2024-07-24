/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
    private class Node {
        private T value;
        private Node left, right;

        public Node(T value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(T value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }
        if (value.compareTo(node.value) < 0) {
            node.left = addRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = addRecursive(node.right, value);
        }
        return node;
    }

    public boolean remove(T value) {
        if (find(value)) {
            root = removeRecursive(root, value);
            return true;
        }
        return false;
    }

    private Node removeRecursive(Node node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = removeRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = removeRecursive(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.value = findMin(node.right);
            node.right = removeRecursive(node.right, node.value);
        }
        return node;
    }

    private T findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    public boolean find(T value) {
        return findRecursive(root, value);
    }

    private boolean findRecursive(Node node, T value) {
        if (node == null) {
            return false;
        }

        if (value.compareTo(node.value) < 0) {
            return findRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            return findRecursive(node.right, value);
        } else {
            return true;
        }
    }

    public void printInOrder() {
        printInOrderRecursive(root);
    }

    private void printInOrderRecursive(Node node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.println(node.value);
            printInOrderRecursive(node.right);
        }
    }

    public void printDescendingOrder() {
        printDescendingOrderRecursive(root);
    }

    private void printDescendingOrderRecursive(Node node) {
        if (node != null) {
            printDescendingOrderRecursive(node.right);
            System.out.println(node.value);
            printDescendingOrderRecursive(node.left);
        }
    }

    public void writeInOrder(BufferedWriter writer) throws IOException {
        writeInOrderRecursive(root, writer);
    }

    private void writeInOrderRecursive(Node node, BufferedWriter writer) throws IOException {
        if (node != null) {
            writeInOrderRecursive(node.left, writer);
            writer.write(node.value.toString());
            writer.newLine();
            writeInOrderRecursive(node.right, writer);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<T> {
        private Stack<Node> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            Node node = stack.pop();
            T result = node.value;
            if (node.right != null) {
                pushLeft(node.right);
            }
            return result;
        }
    }
}

