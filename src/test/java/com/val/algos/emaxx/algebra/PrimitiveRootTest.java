package com.val.algos.emaxx.algebra;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveRootTest {

    @Test
    public void testGenerator() {
        Assert.assertEquals(1, PrimitiveRoot.generator(2)); // 1
        Assert.assertEquals(2, PrimitiveRoot.generator(3)); // 2^1=2, 2^2=4=1. Order 2.
        Assert.assertEquals(2, PrimitiveRoot.generator(5)); // 2^1=2, 2^2=4, 2^3=8=3, 2^4=16=1. Order 4.
        Assert.assertEquals(3, PrimitiveRoot.generator(7)); // 3^1=3, 3^2=2, 3^3=6, 3^4=4, 3^5=5, 3^6=1. Order 6.
        Assert.assertEquals(3, PrimitiveRoot.generator(17));
    }
}
