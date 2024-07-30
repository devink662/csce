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

    public T findMin() {
        if (root == null) {
            return null;
        }
        return findMin(root);
    }

    public T findMax() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
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

    public String descendingOrder() {
        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.right;
            } else {
                current = stack.pop();
                sb.append(current.value).append("\n");
                current = current.left;
            }
        }
        return sb.toString();
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
            Node node = stack.peek();
            T result = node.value;
            if (node.right != null) {
                pushLeft(node.right);
            }
            return result;
        }
    }
}
