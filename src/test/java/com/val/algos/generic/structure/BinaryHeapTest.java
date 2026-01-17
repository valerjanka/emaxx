package com.val.algos.generic.structure;

import org.junit.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
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

    @Test
    public void testMemoryLeak() throws Exception {
        BinaryHeap<Integer> heap = new BinaryHeap<>(10);
        for (int i = 0; i < 1000; i++) {
            heap.offer(i);
            heap.poll();
        }

        // Check internal ArrayList size via reflection
        Field elementsField = BinaryHeap.class.getDeclaredField("elements");
        elementsField.setAccessible(true);
        ArrayList<?> elements = (ArrayList<?>) elementsField.get(heap);

        // If correct, size should be small (e.g. 1 or 2, just the dummy null and maybe one slot).
        // If leaking, size will be ~1000.
        // Current implementation starts with null, so index 0 is used.
        // If we offer/poll 1000 times, and it never removes, size should be 1001.

        assertTrue("Internal list size (" + elements.size() + ") should be small but was not. Memory leak detected.",
                   elements.size() < 100);
    }
}
