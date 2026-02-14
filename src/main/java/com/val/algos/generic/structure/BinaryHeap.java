package com.val.algos.generic.structure;

import java.util.ArrayList;

/**
 * Generic min binary heap implementation
 * <p>
 * Space Complexity: O(N)
 *
 * @author valerjanka
 */
public class BinaryHeap<T extends Comparable<T>> {
    /**
     * Stores elements of the heap.
     * The first element (index 0) is a dummy null to simplify 1-based indexing calculations (children at 2k, 2k+1).
     */
    private final ArrayList<T> elements;

    public BinaryHeap(int size) {
        elements = new ArrayList<>(size);
        elements.add(null);
    }

    /**
     * Inserts the specified element into this heap.
     * <p>
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     *
     * @param element the element to add
     */
    public void offer(T element) {
        elements.add(element);
        swimUp();
    }

    /**
     * Retrieves and removes the head (minimum element) of this heap.
     * <p>
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     *
     * @return the head of this heap
     * @throws IllegalStateException if this heap is empty
     */
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Can't delete min element from empty queue");
        }
        T result = elements.get(1);
        int lastIndex = size();
        swap(1, lastIndex);
        elements.remove(lastIndex);

        if (!isEmpty()) {
            swimDown();
        }
        return result;
    }

    /**
     * Returns the number of elements in the heap.
     * Subtracts 1 because the first element is a dummy.
     */
    private int size() {
        return elements.size() - 1;
    }

    /**
     * Checks if the heap is empty.
     */
    private boolean isEmpty() {
        return size() == 0;
    }

    private void swimDown() {
        int k = 1;
        int currentSize = size();
        T element = elements.get(k);
        while (2 * k <= currentSize) {
            int minChild = getMinChild(k, currentSize);
            if (element.compareTo(elements.get(minChild)) > 0) {
                swap(k, minChild);
                k = minChild;
            } else {
                return;
            }
        }
    }

    private int getMinChild(int parent, int currentSize) {
        if (2 * parent + 1 <= currentSize) {
            return (elements.get(2 * parent).compareTo(elements.get(2 * parent + 1)) < 0) ? 2 * parent : 2 * parent + 1;
        } else {
            return 2 * parent;
        }
    }

    private void swimUp() {
        int index = size();
        T element = elements.get(index);
        while (index > 1 && elements.get(index / 2).compareTo(element) > 0) {
            swap(index / 2, index);
            index /= 2;
        }
    }

    private void swap(int parentIndex, int index) {
        T element = elements.get(parentIndex);
        elements.set(parentIndex, elements.get(index));
        elements.set(index, element);
    }
}
