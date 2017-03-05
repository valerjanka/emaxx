package com.val.emaxx.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void testBinary() throws Exception {
        assertEquals(1, Fibonacci.binary(1));
        assertEquals(1, Fibonacci.binary(0));
        assertEquals(2, Fibonacci.binary(2));
        assertEquals(3, Fibonacci.binary(3));
        assertEquals(5, Fibonacci.binary(4));
        assertEquals(8, Fibonacci.binary(5));
        assertEquals(13, Fibonacci.binary(6));
        assertEquals(21, Fibonacci.binary(7));
    }
}