package com.val.algos.generic.structure;

import org.junit.Test;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.Assert.*;

public class BinaryHeapTest {

    @Test
    public void testBasicOperations() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(10);
        heap.offer(5);
        heap.offer(1);
        heap.offer(3);

        assertEquals(Integer.valueOf(1), heap.poll());
        assertEquals(Integer.valueOf(3), heap.poll());
        assertEquals(Integer.valueOf(5), heap.poll());
    }

    @Test(expected = IllegalStateException.class)
    public void testPollEmpty() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(10);
        heap.poll();
    }

    @Test
    public void testMixedOperations() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(10);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random rand = new Random(42);

        // Add 100 elements
        for (int i = 0; i < 100; i++) {
            int val = rand.nextInt(1000);
            heap.offer(val);
            pq.offer(val);
        }

        // Remove 50
        for (int i = 0; i < 50; i++) {
            assertEquals(pq.poll(), heap.poll());
        }

        // Add 50 more
        for (int i = 0; i < 50; i++) {
            int val = rand.nextInt(1000);
            heap.offer(val);
            pq.offer(val);
        }

        // Drain all
        while (!pq.isEmpty()) {
            assertEquals(pq.poll(), heap.poll());
        }

        // Ensure heap handles empty correctly after drain
        try {
            heap.poll();
            fail("Should throw exception");
        } catch (IllegalStateException e) {
            // expected
        }
    }
}
