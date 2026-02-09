package com.val.algos.generic.algebra.search;

/**
 * @author valerjanka
 */
public class StopConditionBinarySearch {

    /**
     * Search with specified stop condition and returns index of an element if stop condition returns 0 or -1 otherwise.
     * <p>
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param n             do binary search from 0 to n-1
     * @param stopCondition checks index by some condition and returns 0 if it's result index,
     *                      less than 0 to search on the left part or more than 0 to search on the right part
     * @return index of searched element or -1 if not found
     */
    public static int search(int n, StopCondition stopCondition) {
        int l = 0;
        int r = n; // exclusive, to do not check element after while when l == r.
        while (l < r) {
            int m = (l + r) / 2;
            int comparison = stopCondition.compare(m);
            if (comparison == 0) {
                return m;
            } else if (comparison > 0) { // on the right
                l = m + 1; // inclusive
            } else { // on the left
                r = m; // exclusive
            }
        }
        return -1;
    }

    /**
     * Search for element position in given array by using StopCondition
     * <p>
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param array   to be searched
     * @param element to search
     * @return position of result element or -1
     */
    public static int searchAny(int[] array, int element) {
        StopCondition stopCondition = (position) -> Integer.compare(array[position], element);
        return search(array.length, stopCondition);
    }

    /**
     * Search for the first occurrence of the target element.
     * <p>
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param array  to be searched
     * @param target to search
     * @return index of the first occurrence or -1
     */
    public static int searchFirst(int[] array, int target) {
        StopOnFirstExistCondition stopCondition = new StopOnFirstExistCondition(array, target);
        return search(array.length, stopCondition);
    }

    /**
     * Search for the first element greater than the target.
     * <p>
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param array  to be searched
     * @param target to search
     * @return index of the first greater element or -1
     */
    public static int searchFirstGreater(int[] array, int target) {
        IntNextGreaterToCondition stopCondition = new IntNextGreaterToCondition(array, target);
        return search(array.length, stopCondition);
    }

    public interface StopCondition {
        /**
         * @param position to check
         * @return less than 0, if stopPosition is on the left. More than 0, if stopPosition is on the right. 0 - to
         * stop.
         */
        int compare(int position);
    }
}
