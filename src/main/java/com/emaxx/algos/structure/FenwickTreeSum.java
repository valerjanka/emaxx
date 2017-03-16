package com.emaxx.algos.structure;

/**
 * tree[i] = sum of a[j], where j from [F(i) ; i],
 * F(i) = i & (i+1) (it equals i if last bit is 1, otherwise i without all last group of 1s)
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
 * @author valerii.ryzhuk
 *
 */
public class FenwickTreeSum
{
    int[] tree;
    public FenwickTreeSum(int[] a) {
        tree = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            inc(i, a[i]);
        }
    }

    /**
     * Updates positions x = (x | (x + 1)) on delta
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
