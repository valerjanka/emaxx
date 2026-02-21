package com.val.algos.emaxx.structure;

/**
 * <a href="http://e-maxx.ru/algo/fenwick_tree">http://e-maxx.ru/algo/fenwick_tree</a>
 * <p>
 * tree[i] = sum of a[j], where j from [F(i) ; i],
 * F(i) = i & (i+1) (it equals i if last bit is 0, otherwise i without all last group of 1s)
 * <br>
 * <b>SUM</b>: to find sum from [0, r] we need to sum next values from tree:<br>
 * 1) [F(r)...r] = tree[r] <br>
 * 2) [F(F(r)-1)...F(r)-1] = tree[F(r)-1] <br>
 * ... until we cover all array prefix <br>
 * Recursive formula: <br>
 *     1) result += tree[r] <br>
 *     2) <b>r = (r & (r+1)) - 1; while r >= 0</b> repeat from 1 <br>
 * <b>INC</b>: to update element j, we need to update all elements from tree that cover j: <br>
 *     1) tree[j] += delta <br>
 *     2) <b>j = j | (j+1); while j < len</b> repeat from 1 <br>
 * <p>
 * Time Complexity: O(log N) for both sum and update operations.
 * Space Complexity: O(N) to store the tree.
 */
public class FenwickTreeSum
{
    int[] tree;

    /**
     * Constructs a Fenwick Tree from the given array.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param a the input array
     */
    public FenwickTreeSum(int[] a) {
        int n = a.length;
        tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] += a[i];
            int j = i | (i + 1);
            if (j < n) {
                tree[j] += tree[i];
            }
        }
    }

    /**
     * Updates positions x = (x | (x + 1)) on delta
     * <p>
     * Time Complexity: O(log N)
     *
     * @param position of element in array
     * @param delta add to element
     */
    public void inc(int position, int delta) {
        for(int x = position; x < tree.length; x = (x | (x + 1))) {
            tree[x] += delta;
        }
    }

    /**
     * Sums elements from position l to r inclusively
     * <p>
     * Time Complexity: O(log N)
     *
     * @param l from position
     * @param r to position
     * @return sum
     */
    public int sum(int l, int r) {
        return sum(r) - sum(l-1);
    }

    /**
     * Sums element from 0 to r inclusively
     * <p>
     * Time Complexity: O(log N)
     *
     * @param r to position
     * @return sum
     */
    public int sum(int r) {
        int result = 0;
        for(int x = r; x >= 0; x = (x & (x + 1)) - 1) {
            result += tree[x];
        }
        return result;
    }
}
