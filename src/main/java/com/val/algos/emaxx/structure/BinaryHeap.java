package com.val.algos.emaxx.structure;

/**
 * Min Binary Heap implementation similar to {@link java.util.PriorityQueue}.
 * <p>
 * Time Complexity: O(log N) for offer and poll, O(1) for peek.
 * Space Complexity: O(N) to store the elements.
 *
 * @author valerjanka
 */
public class BinaryHeap {
    private final int[] keys;
    private int size = 0;

    /**
     * Constructs a Binary Heap with the specified capacity.
     * <p>
     * Space Complexity: O(N)
     *
     * @param size the maximum number of elements the heap can hold
     */
    public BinaryHeap(int size) {
        this.keys = new int[size + 1];
    }

    /**
     * Inserts the specified element into this priority queue.
     * <p>
     * Time Complexity: O(log N)
     *
     * @param key the element to add
     */
    public void offer(int key) {
        ++size;
        keys[size] = key;
        swim(size);
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     * <p>
     * Time Complexity: O(1)
     *
     * @return the head of this queue
     * @throws IllegalStateException if this queue is empty
     */
    public int peek() {
        if(size == 0) {
            throw new IllegalStateException("Can't peek min element from empty queue");
        }
        return keys[1];
    }

    /**
     * Retrieves and removes the head of this queue.
     * <p>
     * Time Complexity: O(log N)
     *
     * @return the head of this queue
     * @throws IllegalStateException if this queue is empty
     */
    public int poll() {
        if(size == 0) {
            throw new IllegalStateException("Can't poll min element from empty queue");
        }
        int result = keys[1];
        keys[1] = keys[size];
        --size;
        propagate();
        return result;
    }

    private void propagate() {
        int k = 1;
        while(2*k <= size) {
            int minChildPosition = getMinChildPosition(k);
            if(keys[k] > keys[minChildPosition]) {
                swap(k, minChildPosition);
            } else {
                return;
            }
        }
    }

    private int getMinChildPosition(int k) {
        if(2*k+1 <= size) {
            return Math.min(keys[2 * k], keys[2 * k + 1]);
        } else {
            return keys[2*k];
        }
    }

    private void swim(int k) {
        while(k > 1 && keys[k/2] > keys[k]) {
            swap(k, k/2);
            k = k /2;
        }
    }

    private void swap(int k, int r) {
        int x = keys[k];
        keys[k] = keys[r];
        keys[r] = x;
    }

}
