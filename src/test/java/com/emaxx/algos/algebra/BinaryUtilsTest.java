package com.emaxx.algos.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryUtilsTest {

    @Test
    public void testBinpow() throws Exception {
        assertEquals(2, BinaryUtils.pow(2, 3, 3)); // 8 % 3 = 2
        assertEquals(32, BinaryUtils.pow(2, 5, 100)); // 8 % 3 = 2
        assertEquals(1, BinaryUtils.pow(2, 4, 3)); // 16 % 3 = 1
        assertEquals(0, BinaryUtils.pow(100, 100, 100)); // 100^100 % 100 = 0
    }

    @Test
    public void testBinMultiply() throws Exception {
        assertEquals(2, BinaryUtils.multiply(2, 3, 4)); // 2 * 3 % 4 = 2
        assertEquals(2, BinaryUtils.multiply(2, 4, 3)); // 2 * 4 % 3 = 2
        assertEquals(0, BinaryUtils.multiply(Long.MAX_VALUE / 2, Long.MAX_VALUE / 2, Long.MAX_VALUE / 2)); // max * max % max = 0
        assertEquals(0, BinaryUtils.multiply(Long.MAX_VALUE / 2, 2, Long.MAX_VALUE - 1));
        assertEquals(0, BinaryUtils.multiply(100, 100, 100)); // 100 * 100 % 100 = 0
    }
}
