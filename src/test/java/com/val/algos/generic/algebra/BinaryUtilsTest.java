package com.val.algos.generic.algebra;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author valerjanka
 */
public class BinaryUtilsTest {

    @Test
    public void testBinPow() {
        assertEquals(1024, BinaryUtils.binary(2, 10, BinaryUtils.createLongPowAction(10000))); // 8 % 3 = 2
        assertEquals(2, BinaryUtils.binary(2, 3, BinaryUtils.createLongPowAction(3))); // 8 % 3 = 2
        assertEquals(32, BinaryUtils.binary(2, 5, BinaryUtils.createLongPowAction(100))); // 8 % 3 = 2
        assertEquals(1, BinaryUtils.binary(2, 4, BinaryUtils.createLongPowAction(3))); // 16 % 3 = 1
        assertEquals(0, BinaryUtils.binary(100, 100, BinaryUtils.createLongPowAction(100))); // 100^100 % 100 = 0
    }

    @Test
    public void testBinPowGeneric() {
        assertEquals(1024, (long) BinaryUtils.binary(Long.valueOf(2), 10, BinaryUtils.createLongPowAction(10000))); // 8 % 3 = 2
        assertEquals(2, (long) BinaryUtils.binary(Long.valueOf(2), 3, BinaryUtils.createLongPowAction(3))); // 8 % 3 = 2
        assertEquals(32, (long) BinaryUtils.binary(Long.valueOf(2), 5, BinaryUtils.createLongPowAction(100))); // 8 % 3 = 2
        assertEquals(1, (long) BinaryUtils.binary(Long.valueOf(2), 4, BinaryUtils.createLongPowAction(3))); // 16 % 3 = 1
        assertEquals(0, (long) BinaryUtils.binary(Long.valueOf(100), 100, BinaryUtils.createLongPowAction(100))); // 100^100 % 100 = 0
    }

    @Test
    public void testBinMultiply() {
        assertEquals(2, BinaryUtils.binary(2, 3, BinaryUtils.createLongMultiplyAction(4))); // 2 * 3 % 4 = 2
        assertEquals(2, BinaryUtils.binary(2, 4, BinaryUtils.createLongMultiplyAction(3))); // 2 * 4 % 3 = 2
        assertEquals(0, BinaryUtils.binary(Long.MAX_VALUE / 2, Long.MAX_VALUE / 2, BinaryUtils.createLongMultiplyAction(Long.MAX_VALUE / 2))); // max/2 + max/2 % max/2 = 0
        assertEquals(0, BinaryUtils.binary(Long.MAX_VALUE / 2, 2, BinaryUtils.createLongMultiplyAction(Long.MAX_VALUE - 1)));
        assertEquals(0, BinaryUtils.binary(100, 100, BinaryUtils.createLongMultiplyAction(100))); // 100 * 100 % 100 = 0
    }

    @Test
    public void testBinMultiplyGeneric() {
        assertEquals(2, (long) BinaryUtils.binary(Long.valueOf(2), 3, BinaryUtils.createLongMultiplyAction(4))); // 2 * 3 % 4 = 2
        assertEquals(2, (long) BinaryUtils.binary(Long.valueOf(2), 4, BinaryUtils.createLongMultiplyAction(3))); // 2 * 4 % 3 = 2
        assertEquals(0, (long) BinaryUtils.binary(Long.valueOf(Long.MAX_VALUE / 2), Long.MAX_VALUE / 2, BinaryUtils.createLongMultiplyAction(Long.MAX_VALUE / 2))); // max/2 + max/2 % max/2 = 0
        assertEquals(0, (long) BinaryUtils.binary(Long.valueOf(Long.MAX_VALUE / 2), 2, BinaryUtils.createLongMultiplyAction(Long.MAX_VALUE - 1)));
        assertEquals(0, (long) BinaryUtils.binary(Long.valueOf(100), 100, BinaryUtils.createLongMultiplyAction(100))); // 100 * 100 % 100 = 0
    }
}