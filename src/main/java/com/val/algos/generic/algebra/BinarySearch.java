package com.val.algos.generic.algebra;

import java.util.ArrayList;

/**
 * @author valerjanka
 */
public class BinarySearch {

    /**
     * Search for element position in given array
     * @param array to be searched
     * @param element to search
     * @return position of result element or -1
     */
    public static int searchExist(int[] array, int element) {
        int l = 0;
        int r = array.length;
        while(l < r) {
            int m = (l + r) / 2;
            int comparison = element - array[m];
            if(comparison == 0) {
                return m;
            } else if(comparison > 0) { // on the right
                l = m + 1; // exclusive
            } else { // on the left
                r = m; // exclusive
            }
        }
        return -1;
    }

    /**
     * Search for position in given array that satisfied given condition or -1  if not found
     * @param array to be searched
     * @param condition to check
     * @param <T> type of elements in given array
     * @return position of result element or -1
     */
    public static <T> int search(T[] array, ArrayStopCondition<T> condition) {
        int l = 0;
        int r = array.length;
        while(l < r) {
            int m = (l + r) / 2;
            int comparison = condition.compareAndCheck(array, m);
            if(comparison == 0) {
                return m;
            } else if(comparison > 0) { // on the right
                l = m + 1; // exclusive
            } else { // on the left
                r = m; // exclusive
            }
        }
        return -1;
    }

    /**
     * Search for position in given array that satisfied given condition or -1  if not found
     * @param list to be searched
     * @param condition to check
     * @param <T> type of elements in given array
     * @return position of result element or -1
     */
    public static <T> int search(ArrayList<T> list, ListStopCondition<T> condition) {
        int l = 0;
        int r = list.size();
        while(l < r) {
            int m = (l + r) / 2;
            int comparison = condition.compareAndCheck(list, m);
            if(comparison == 0) {
                return m;
            } else if(comparison > 0) { // on the right
                l = m + 1; // exclusive
            } else { // on the left
                r = m; // exclusive
            }
        }
        return -1;
    }

    /**
     * Search for exact element in given array
     * @param array where to search
     * @param element to search
     * @param <T> type of comparable elements
     * @return position of element or -1
     */
    public static <T extends Comparable<T>> int searchForElement(T[] array, T element) {
        ArrayStopCondition<T> condition = (array1, position) -> element.compareTo(array1[position]);
        return search(array, condition);
    }

    public interface ArrayStopCondition<T> {
        /**
         * Compare result element position with argument position.
         * Equivalent to resultPosition.compareTo(position)
         *
         * @param array to search
         * @param position position to compare
         * @return 0 if it is our result position, less than 0 if result position is on the left and more than 0 if on the right
         */
        int compareAndCheck(T[] array, int position);
    }

    public interface ListStopCondition<T> {
        /**
         * Compare result element position with argument position.
         * Equivalent to resultPosition.compareTo(position)
         *
         * @param array to search
         * @param position position to compare
         * @return 0 if it is our result position, less than 0 if result position is on the left and more than 0 if on the right
         */
        int compareAndCheck(ArrayList<T> array, int position);
    }
}
