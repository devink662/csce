import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void add(T value) {
        heap.add(value);
        bubbleUp(heap.size() - 1);
    }

    public T remove() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T result = heap.get(0);
        T lastValue = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            bubbleDown(0);
        }
        return result;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    private void bubbleUp(int index) {
        int parentIndex;
        while (index > 0) {
            parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void bubbleDown(int index) {
        int leftChild, rightChild, smallestChild;
        while (index < heap.size() / 2) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallestChild = leftChild;

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                smallestChild = rightChild;
            }

            if (heap.get(index).compareTo(heap.get(smallestChild)) <= 0) {
                break;
            }

            swap(index, smallestChild);
            index = smallestChild;
        }
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
