package com.val.emaxx.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author valerjanka
 */
public class PrimesTest {

    @Test
    public void testLowestPrimes() throws Exception {
        assertArrayEquals(new int[] {0, 0, 2, 3, 2, 5, 2, 7, 2, 3, 2, 11, 2, 13, 2, 3, 2, 17, 2, 19, 2, 3, 2, 23, 2, 5, 2, 3, 2, 29}, new Primes().lowestPrimes(29));
    }
}