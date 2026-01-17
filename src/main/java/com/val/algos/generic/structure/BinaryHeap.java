package com.val.algos.generic.structure;

import java.util.ArrayList;

/**
 * Generic min binary heap implementation
 *
 * @author valerjanka
 */
public class BinaryHeap<T extends Comparable<T>> {
    private final ArrayList<T> elements;
    private int realSize;

    public BinaryHeap(int size) {
        elements = new ArrayList<>(size);
        elements.add(null);
    }

    public void offer(T element) {
        elements.add(element);
        realSize++;
        swimUp();
    }

    public T poll() {
        if (realSize == 0) {
            throw new IllegalStateException("Can't delete min element from empty queue");
        }
        T result = elements.get(1);
        swap(1, realSize);
        elements.remove(realSize);
        --realSize;
        if (realSize > 0) {
            swimDown();
        }
        return result;
    }

    private void swimDown() {
        int k = 1;
        T element = elements.get(k);
        while (2 * k <= realSize) {
            int minChild = getMinChild(k);
            if (element.compareTo(elements.get(minChild)) > 0) {
                swap(k, minChild);
                k = minChild;
            } else {
                return;
            }
        }
    }

    private int getMinChild(int parent) {
        if (2 * parent + 1 <= realSize) {
            return (elements.get(2 * parent).compareTo(elements.get(2 * parent + 1)) < 0) ? 2 * parent : 2 * parent + 1;
        } else {
            return 2 * parent;
        }
    }

    private void swimUp() {
        int index = realSize;
        T element = elements.get(realSize);
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
