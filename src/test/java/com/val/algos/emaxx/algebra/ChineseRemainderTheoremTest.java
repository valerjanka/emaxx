package com.val.algos.emaxx.algebra;

import org.junit.Assert;
import org.junit.Test;

public class ChineseRemainderTheoremTest {

    @Test
    public void testSolve() {
        // x = 2 mod 3
        // x = 3 mod 5
        // x = 2 mod 7
        // 23 satisfies all.
        long[] a = {2, 3, 2};
        long[] m = {3, 5, 7};
        Assert.assertEquals(23, ChineseRemainderTheorem.solve(a, m));

        // Example 2
        // x = 1 mod 2
        // x = 2 mod 3
        // x = 3 mod 5
        // 23 satisfies all.
        long[] a2 = {1, 2, 3};
        long[] m2 = {2, 3, 5};
        Assert.assertEquals(23, ChineseRemainderTheorem.solve(a2, m2));
    }
}
