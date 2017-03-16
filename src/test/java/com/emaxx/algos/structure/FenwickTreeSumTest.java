package com.emaxx.algos.structure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author valerii.ryzhuk
 *
 * @version $Id:$
 * @since 16.3
 */
public class FenwickTreeSumTest
{

    @Test
    public void testInc() throws Exception
    {
        int[] a = {0, 0, 0, 0};
        FenwickTreeSum tree = new FenwickTreeSum(a);
        assertArrayEquals(new int[]{0,0,0,0}, tree.tree);
        tree.inc(1, 1);
        assertArrayEquals(new int[]{0,1,0,1}, tree.tree);
        tree.inc(2,2);
        assertArrayEquals(new int[]{0,1,2,3}, tree.tree);
    }

    @Test
    public void testSum() throws Exception
    {
        int[] a = {1, 2, 3, 4};
        FenwickTreeSum tree = new FenwickTreeSum(a);
        assertArrayEquals(new int[]{1,3,3,10}, tree.tree);
        assertEquals(1, tree.sum(0, 0));
        assertEquals(3, tree.sum(0, 1));
        assertEquals(2, tree.sum(1, 1));
        assertEquals(9, tree.sum(1, 3));
        assertEquals(7, tree.sum(2, 3));
    }
}
