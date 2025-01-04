package com.val.algos.generic.structure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SqrtDecompositionTest {

    private SqrtDecomposition<Integer> minFunction;

    @Before
    public void setUp() {
        Integer[] items = {5, 3, 8, 6, 2, 7, 4, 1};
        minFunction = new SqrtDecomposition<>(items, new SqrtDecomposition.MinFunction());
    }

    @Test
    public void testGetMinimumInFullArray() {
        // Test querying the entire array
        assertEquals(1, (int) minFunction.get(0, 8));
    }

    @Test
    public void testGetMinimumInSubArray() {
        // Test querying a subset of the array
        assertEquals(2, (int) minFunction.get(1, 5)); // From index 1 to 4: 3, 8, 6, 2
    }

    @Test
    public void testGetMinimumWithSingleElement() {
        assertEquals(8, (int) minFunction.get(2, 3));
    }

    @Test
    public void testGetMinimumInDifferentSegments() {
        assertEquals(1, (int) minFunction.get(5, 8)); // From index 5 to 7: 7, 4, 1
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMinimumWithEndIndexOutOfBounds() {
        minFunction.get(0, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMinimumWithStartIndexOutOfBounds() {
        minFunction.get(10, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinimumWithInvalidIndices() {
        minFunction.get(5, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMinimumWithNegativeIndex() {
        minFunction.get(-1, 3);
    }
}