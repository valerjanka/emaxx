package com.val.algos.other.sort;

import org.junit.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IntRadixSortTest {

    private final IntRadixSort sorter = new IntRadixSort();

    @Test
    public void testPositiveIntegers() {
        int[] input = {10, 100, 1, 5, 23, 99};
        int[] expected = {1, 5, 10, 23, 99, 100};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    public void testNegativeIntegers() {
        int[] input = {-10, -100, -1, -5, -23, -99};
        int[] expected = {-100, -99, -23, -10, -5, -1};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    public void testMixedIntegers() {
        int[] input = {10, -100, 1, -5, 23, -99, 0};
        int[] expected = {-100, -99, -5, 0, 1, 10, 23};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    public void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    public void testSingleElementArray() {
        int[] input = {42};
        int[] expected = {42};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    public void testNullArray() {
        int[] input = null;
        assertArrayEquals(null, sorter.sort(input));
    }

    @Test
    public void testLargeArray() {
        int size = 1000;
        int[] input = new int[size];
        Random rand = new Random(42);
        for (int i = 0; i < size; i++) {
            input[i] = rand.nextInt();
        }
        int[] expected = Arrays.copyOf(input, size);
        Arrays.sort(expected);
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    public void testLargeValues() {
        int[] input = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] expected = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        assertArrayEquals(expected, sorter.sort(input));
    }
}
