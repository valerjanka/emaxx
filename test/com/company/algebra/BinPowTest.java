package com.company.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinPowTest {

    @Test
    public void testBinpow() throws Exception {
        assertEquals(2, BinPow.binpow(2, 3, 3)); // 8 % 3 = 2
        assertEquals(1, BinPow.binpow(2, 4, 3)); // 16 % 3 = 1
        assertEquals(0, BinPow.binpow(100, 100, 100)); // 100^100 % 100 = 0
    }
}