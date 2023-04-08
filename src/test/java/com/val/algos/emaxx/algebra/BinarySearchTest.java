package com.val.algos.emaxx.algebra;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author valerjanka
 */
public class BinarySearchTest {

    @Test
    public void searchExistInEvenArray() {
        int[] array = new int[]{1, 2, 3, 4};
        assertEquals(-1, BinarySearch.searchExist(array, 0));
        assertEquals(0, BinarySearch.searchExist(array, 1));
        assertEquals(1, BinarySearch.searchExist(array, 2));
        assertEquals(2, BinarySearch.searchExist(array, 3));
        assertEquals(3, BinarySearch.searchExist(array, 4));
        assertEquals(-1, BinarySearch.searchExist(array, 5));
    }

    @Test
    public void searchExistInOddArray() {
        int[] array = new int[]{1, 2, 3};
        assertEquals(-1, BinarySearch.searchExist(array, 0));
        assertEquals(0, BinarySearch.searchExist(array, 1));
        assertEquals(1, BinarySearch.searchExist(array, 2));
        assertEquals(2, BinarySearch.searchExist(array, 3));
        assertEquals(-1, BinarySearch.searchExist(array, 4));
    }

}