package com.val.algos.emaxx.algebra;

import org.junit.Assert;
import org.junit.Test;

public class EulerFunctionTest {

    @Test
    public void testPhi() {
        Assert.assertEquals(1, EulerFunction.phi(1));
        Assert.assertEquals(1, EulerFunction.phi(2)); // 1
        Assert.assertEquals(2, EulerFunction.phi(3)); // 1, 2
        Assert.assertEquals(2, EulerFunction.phi(4)); // 1, 3
        Assert.assertEquals(4, EulerFunction.phi(5)); // 1, 2, 3, 4
        Assert.assertEquals(2, EulerFunction.phi(6)); // 1, 5
        Assert.assertEquals(4, EulerFunction.phi(10)); // 1, 3, 7, 9
        Assert.assertEquals(12, EulerFunction.phi(13));
        Assert.assertEquals(12, EulerFunction.phi(36));
        Assert.assertEquals(9972, EulerFunction.phi(9973)); // 9973 is prime
    }

    @Test
    public void testPhi1ToN() {
        int n = 10;
        int[] result = EulerFunction.phi1ToN(n);
        Assert.assertEquals(n + 1, result.length);
        Assert.assertEquals(1, result[1]);
        Assert.assertEquals(1, result[2]);
        Assert.assertEquals(2, result[3]);
        Assert.assertEquals(2, result[4]);
        Assert.assertEquals(4, result[5]);
        Assert.assertEquals(2, result[6]);
        Assert.assertEquals(6, result[7]);
        Assert.assertEquals(4, result[8]);
        Assert.assertEquals(6, result[9]);
        Assert.assertEquals(4, result[10]);
    }
}
