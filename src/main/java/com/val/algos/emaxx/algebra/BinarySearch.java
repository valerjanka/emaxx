package com.val.algos.emaxx.algebra;

/**
 * Created 4/11/2019
 *
 * @author valerjanka
 */
public class BinarySearch {
    /**
     * Search for element position in given sorted array
     * <p>
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     *
     * @param array   to be searched. Should be sorted.
     * @param element to search
     * @return position of the result element or -1
     */
    public static int searchExist(int[] array, int element) {
        int l = 0;
        int r = array.length;
        while (l < r) {
            int m = (l + r) / 2;
            int comparison = element - array[m];
            if (comparison == 0) {
                return m;
            } else if (comparison > 0) { // on the right
                l = m + 1; // exclusive
            } else { // on the left
                r = m; // exclusive
            }
        }
        return -1;
    }

}
