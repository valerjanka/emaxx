package com.val.algos.emaxx.structure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author valerjanka
 */
public class BinaryHeapTest {
    @Test
    public void offer() throws Exception {
        BinaryHeap binaryHeap = new BinaryHeap(5);
        binaryHeap.offer(2);
        Assert.assertEquals(2, binaryHeap.peek());
        binaryHeap.offer(5);
        Assert.assertEquals(2, binaryHeap.peek());
        binaryHeap.offer(3);
        Assert.assertEquals(2, binaryHeap.peek());
        binaryHeap.offer(1);
        Assert.assertEquals(1, binaryHeap.peek());

    }

    @Test
    public void poll() throws Exception {
        BinaryHeap binaryHeap = new BinaryHeap(5);
        binaryHeap.offer(2);
        binaryHeap.offer(1);
        Assert.assertEquals(1, binaryHeap.poll());
        binaryHeap.offer(5);
        Assert.assertEquals(2, binaryHeap.poll());
        Assert.assertEquals(5, binaryHeap.poll());
        binaryHeap.offer(3);
        Assert.assertEquals(3, binaryHeap.poll());
        binaryHeap.offer(2);
        binaryHeap.offer(1);
        Assert.assertEquals(1, binaryHeap.poll());
        Assert.assertEquals(2, binaryHeap.poll());
    }


    @Test(expected = IllegalStateException.class)
    public void pollFromEmpty() throws Exception {
        BinaryHeap binaryHeap = new BinaryHeap(5);
        binaryHeap.offer(2);
        Assert.assertEquals(2, binaryHeap.poll());
        binaryHeap.poll();
    }

}