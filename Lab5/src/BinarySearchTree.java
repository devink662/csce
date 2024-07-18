/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2 Lab Assignment 5
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 *
 * This class implements a binary search tree to hold Package objects.
 */
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(T data) {
        root = insert(root, data);
    }

    private Node insert(Node node, T data) {
        if (node == null) {
            return new Node(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        return node;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private Node delete(Node node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                node.data = findMin(node.right).data;
                node.right = delete(node.right, node.data);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<T> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.data);
            inOrder(node.right, result);
        }
    }

    public void inOrderTraversal() {
        for (T data : inOrder()) {
            System.out.println(data);
        }
    }
}
