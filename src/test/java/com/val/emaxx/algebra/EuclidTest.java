package com.val.emaxx.algebra;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * User: vryzhuk
 * Date: 5/2/15
 * Time: 2:32 PM
 */
public class EuclidTest {
    @Test
    public void testGcd_recursive() throws Exception {
        assertEquals(3, Euclid.gcd_recursive(3, 0));
        assertEquals(3, Euclid.gcd_recursive(0, 3));
        assertEquals(1, Euclid.gcd_recursive(3, 5));
        assertEquals(1, Euclid.gcd_recursive(5, 3));
        assertEquals(2, Euclid.gcd_recursive(2, 2));
        assertEquals(3, Euclid.gcd_recursive(9, 6));
        assertEquals(4, Euclid.gcd_recursive(8, 12));
    }

    @Test
    public void testGcd_iterative() throws Exception {
        assertEquals(3, Euclid.gcd_iterative(3, 0));
        assertEquals(3, Euclid.gcd_iterative(0, 3));
        assertEquals(1, Euclid.gcd_iterative(3, 5));
        assertEquals(1, Euclid.gcd_iterative(5, 3));
        assertEquals(2, Euclid.gcd_iterative(2, 2));
        assertEquals(3, Euclid.gcd_iterative(9, 6));
        assertEquals(4, Euclid.gcd_iterative(8, 12));
    }

    @Test
    public void testLcm() {
        assertEquals(1, Euclid.lcm(1, 1));
        assertEquals(2, Euclid.lcm(2, 1));
        assertEquals(2, Euclid.lcm(1, 2));
        assertEquals(12, Euclid.lcm(6, 4));
        assertEquals(12, Euclid.lcm(4, 6));
    }

    @Test
    public void testGcd() {
        // 2*2 + 3* (-1) = 1;
        assertEquals(new Euclid.EuclidResult(1, -1, 1),Euclid.gcd(2,3));
        assertEquals(new Euclid.EuclidResult(2, 1, 0),Euclid.gcd(2,4));
        Euclid.EuclidResult result = Euclid.gcd(6, 12);
        assertEquals(6, result.gcd);
        assertEquals(6, 6 * result.x + 12 * result.y);
    }

    @Test
    public void testInverse() {
        assertEquals(1, Euclid.inverse(1, 10));
        assertEquals(7, Euclid.inverse(3, 10));
        assertEquals(6, Euclid.inverse(2, 11));
    }

    @Test
    public void testInverseAll() {
        assertArrayEquals(new int[]{0, 1}, Euclid.inverse(2));
        assertArrayEquals(new int[]{0, 1, 4, 5, 2, 3, 6}, Euclid.inverse(7));
    }

    @Test
    public void testSolve() throws Exception {
        Euclid.EuclidResult result = Euclid.solveDiophantineEquation(2, 1, 3);
        assertEquals(3, 3*result.x + result.y);
        result = Euclid.solveDiophantineEquation(2, 1, 5);
        assertEquals(5, 3*result.x + result.y);
        result = Euclid.solveDiophantineEquation(3, 2, 3);
        assertEquals(3, 3*result.x + 2*result.y);
    }
}
