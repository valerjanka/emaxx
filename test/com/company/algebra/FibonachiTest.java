package com.company.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonachiTest {

    @Test
    public void testBinary() throws Exception {
        assertEquals(1, Fibonachi.binary(1));
        assertEquals(1, Fibonachi.binary(0));
        assertEquals(2, Fibonachi.binary(2));
        assertEquals(3, Fibonachi.binary(3));
        assertEquals(5, Fibonachi.binary(4));
        assertEquals(8, Fibonachi.binary(5));
        assertEquals(13, Fibonachi.binary(6));
        assertEquals(21, Fibonachi.binary(7));
    }
}