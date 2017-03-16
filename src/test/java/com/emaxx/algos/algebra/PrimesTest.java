package com.emaxx.algos.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/27/2015.
 */
public class PrimesTest {

    @Test
    public void testLowestPrimes() throws Exception {
        assertArrayEquals(
            new int[] {0, 0, 2, 3, 2, 5, 2, 7, 2, 3, 2, 11, 2, 13, 2, 3, 2, 17, 2, 19, 2, 3, 2, 23, 2, 5, 2, 3, 2, 29},
            new Primes().lowestPrimes(29));
    }

    @Test
    public void testCalculatePrimes() {
        assertArrayEquals(
            new Integer[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29},
            new Primes().calculatePrimes(29).toArray(new Integer[1]));

        assertEquals(5761455, new Primes().calculatePrimes(100_000_000).size());
    }
}
