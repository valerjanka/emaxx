package com.val.algos.generic.algebra;

import static org.junit.Assert.*;
import org.junit.Test;

public class FibonacciTest {

    @Test
    public void testBinary() throws Exception {
        assertEquals(1, Fibonacci.binary(1));
        assertEquals(0, Fibonacci.binary(0));
        assertEquals(1, Fibonacci.binary(2));
        assertEquals(2, Fibonacci.binary(3));
        assertEquals(3, Fibonacci.binary(4));
        assertEquals(5, Fibonacci.binary(5));
        assertEquals(8, Fibonacci.binary(6));
        assertEquals(13, Fibonacci.binary(7));
    }
}