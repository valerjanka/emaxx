package com.emaxx.algos.structure;

/**
 * Created by valerii.ryzhuk on 1/2/2016.
 *
 * Binary Heap structure satisfying max-heap ordering: the value of each node is less than or equal to the value of its
 * parent.
 */
public class BinaryHeap {
    private int keys[];
    private int size = 0;

    public BinaryHeap(int size) {
        this.keys = new int[size + 1];
    }

    public void offer(int key) {
        ++size;
        keys[size] = key;
        swim(size);
    }

    public int poll() {
        if(size == 0) {
            throw new IllegalStateException("Can't delete max element from empty queue");
        }
        int result = keys[size];
        keys[1] = keys[size];
        --size;
        propagate(1);
        return result;
    }

    private void propagate(int k) {
        while(2*k <= size) {
            int maxChildPosition = getMaxChildPosition(k);
            if(keys[k] < keys[maxChildPosition]) {
                swap(k, maxChildPosition);
                k = maxChildPosition;
            } else {
                return;
            }
        }
    }

    private int getMaxChildPosition(int k) {
        if(2*k+1 <= size) {
            return (keys[2*k] > keys[2*k+1])? keys[2*k] : keys[2*k+1];
        } else {
            return keys[2*k];
        }
    }

    private void swim(int k) {
        while(k > 1 && keys[k] > keys[k/2]) {
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
