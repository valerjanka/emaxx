package com.val.algos.generic.algebra;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author valerjanka
 */
public class BinarySearchTest {
    @Test
    public void searchForElement() throws Exception {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        Assert.assertEquals(0, BinarySearch.searchForElement(array, 1));
        Assert.assertEquals(1, BinarySearch.searchForElement(array, 2));
        Assert.assertEquals(2, BinarySearch.searchForElement(array, 3));
        Assert.assertEquals(3, BinarySearch.searchForElement(array, 4));
        Assert.assertEquals(-1, BinarySearch.searchForElement(array, 5));
    }

    @Test
    public void searchExistInEvenArray() throws Exception {
        int[] array = new int[]{1, 2, 3, 4};
        Assert.assertEquals(0, BinarySearch.searchExist(array, 1));
        Assert.assertEquals(1, BinarySearch.searchExist(array, 2));
        Assert.assertEquals(2, BinarySearch.searchExist(array, 3));
        Assert.assertEquals(3, BinarySearch.searchExist(array, 4));
        Assert.assertEquals(-1, BinarySearch.searchExist(array, 5));
        Assert.assertEquals(-1, BinarySearch.searchExist(array, -5));
    }

    @Test
    public void searchExistInOddArray() throws Exception {
        int[] array = new int[]{1, 2, 3};
        Assert.assertEquals(0, BinarySearch.searchExist(array, 1));
        Assert.assertEquals(1, BinarySearch.searchExist(array, 2));
        Assert.assertEquals(2, BinarySearch.searchExist(array, 3));
        Assert.assertEquals(-1, BinarySearch.searchExist(array, 4));
        Assert.assertEquals(-1, BinarySearch.searchExist(array, -5));
    }

    @Test
    public void searchNextGreaterLast() throws Exception {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        int pivot = 3;
        BinarySearch.ArrayStopCondition<Integer> nextTo3 = getIntegerArrayStopCondition(pivot);
        Assert.assertEquals(3, BinarySearch.search(array, nextTo3));
    }

    @Test
    public void searchNextGreaterFirst() throws Exception {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        int pivot = 0;
        BinarySearch.ArrayStopCondition<Integer> nextTo0 = getIntegerArrayStopCondition(pivot);
        Assert.assertEquals(0, BinarySearch.search(array, nextTo0));
    }

    @Test
    public void searchNextGreaterNotExist() throws Exception {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        int pivot = 4;
        BinarySearch.ArrayStopCondition<Integer> nextTo4 = getIntegerArrayStopCondition(pivot);
        Assert.assertEquals(-1, BinarySearch.search(array, nextTo4));
    }

    private BinarySearch.ArrayStopCondition<Integer> getIntegerArrayStopCondition(int pivot) {
        return (array1, position) -> {
            int compare = array1[position] - pivot;
            if (compare > 0 && position == 0) {
                return 0;
            } else if (compare > 0 && array1[position - 1] <= pivot) {
                return 0;
            }
            return (compare <= 0)? 1 : -1;
        };
    }
}