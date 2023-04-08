package com.val.algos.generic.algebra.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StopConditionBinarySearchTest {

    @Test
    public void searchNextGreaterLast() {
        int[] array = new int[]{1, 2, 3, 4};
        int pivot = 3;
        assertEquals(3, StopConditionBinarySearch.searchFirstGreater(array, pivot));
    }

    @Test
    public void searchNextGreaterFirst() {
        int[] array = new int[]{1, 2, 3, 4};
        int pivot = 0;
        assertEquals(0, StopConditionBinarySearch.searchFirstGreater(array, pivot));
    }

    @Test
    public void searchNextGreaterNotExist() {
        int[] array = new int[]{1, 2, 3, 4};
        int pivot = 4;
        assertEquals(-1, StopConditionBinarySearch.searchFirstGreater(array, pivot));
    }

    @Test
    public void searchFirst() {
        int[] array = new int[]{3, 3, 3, 3};
        int pivot = 3;
        assertEquals(0, StopConditionBinarySearch.searchFirst(array, pivot));
    }

    @Test
    public void searchFirstNotExist() {
        int[] array = new int[]{3, 3, 3};
        int pivot = 2;
        assertEquals(-1, StopConditionBinarySearch.searchFirst(array, pivot));
    }
}