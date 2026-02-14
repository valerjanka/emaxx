package com.val.algos.emaxx.algebra;

import org.junit.Assert;
import org.junit.Test;

public class DiscreteLogarithmTest {

    @Test
    public void testSolve() {
        // 2^x = 8 mod 11 -> x = 3
        Assert.assertEquals(3, DiscreteLogarithm.solve(2, 8, 11));

        // 3^x = 1 mod 7 -> x = 6 (or 0)
        Assert.assertEquals(0, DiscreteLogarithm.solve(3, 1, 7));

        // 5^x = 9 mod 11. 5, 25=3, 15=4, 20=9. x=4.
        Assert.assertEquals(4, DiscreteLogarithm.solve(5, 9, 11));

        // 2^x = 3 mod 5 (2, 4, 3, 1, 2...)
        Assert.assertEquals(3, DiscreteLogarithm.solve(2, 3, 5));

        // No solution: 2^x = 3 mod 7. Powers of 2 mod 7: 2, 4, 1. (Order 3). 3 is not generated.
        Assert.assertEquals(-1, DiscreteLogarithm.solve(2, 3, 7));
    }
}
