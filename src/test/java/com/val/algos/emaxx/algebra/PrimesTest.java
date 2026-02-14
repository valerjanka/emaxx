package com.val.algos.emaxx.algebra;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author valerjanka
 */
public class PrimesTest {

    @Test
    public void testLowestPrimes() {
        int n = 20;
        int[] result = new Primes().lowestPrimes(n);
        Assert.assertEquals(n + 1, result.length);
        Assert.assertEquals(2, result[2]);
        Assert.assertEquals(3, result[3]);
        Assert.assertEquals(2, result[4]);
        Assert.assertEquals(5, result[5]);
        Assert.assertEquals(2, result[6]);
        Assert.assertEquals(7, result[7]);
        Assert.assertEquals(2, result[8]);
        Assert.assertEquals(3, result[9]);
        Assert.assertEquals(2, result[10]);
        Assert.assertEquals(11, result[11]);
        Assert.assertEquals(2, result[12]);
        Assert.assertEquals(13, result[13]);
        Assert.assertEquals(2, result[14]);
        Assert.assertEquals(3, result[15]);
        Assert.assertEquals(2, result[16]);
        Assert.assertEquals(17, result[17]);
        Assert.assertEquals(2, result[18]);
        Assert.assertEquals(19, result[19]);
        Assert.assertEquals(2, result[20]);
    }

    @Test
    public void testIsPrime() {
        Primes primes = new Primes();
        Assert.assertTrue(primes.isPrime(2));
        Assert.assertTrue(primes.isPrime(3));
        Assert.assertFalse(primes.isPrime(4));
        Assert.assertTrue(primes.isPrime(5));
        Assert.assertFalse(primes.isPrime(16));
        Assert.assertTrue(primes.isPrime(17));
        Assert.assertTrue(primes.isPrime(19));
        Assert.assertFalse(primes.isPrime(20));
        Assert.assertTrue(primes.isPrime(9973));
    }

    @Test
    public void testCalculatePrimes() {
        Primes primes = new Primes();
        List<Integer> result10 = primes.calculatePrimes(10);
        Assert.assertEquals(4, result10.size());
        Assert.assertTrue(result10.contains(2));
        Assert.assertTrue(result10.contains(3));
        Assert.assertTrue(result10.contains(5));
        Assert.assertTrue(result10.contains(7));

        List<Integer> result20 = primes.calculatePrimes(20);
        Assert.assertEquals(8, result20.size());
        Assert.assertTrue(result20.contains(2));
        Assert.assertTrue(result20.contains(3));
        Assert.assertTrue(result20.contains(5));
        Assert.assertTrue(result20.contains(7));
        Assert.assertTrue(result20.contains(11));
        Assert.assertTrue(result20.contains(13));
        Assert.assertTrue(result20.contains(17));
        Assert.assertTrue(result20.contains(19));
    }

    @Test
    public void testFactorize() {
        Map<Long, Integer> factors = Primes.factorize(2 * 2 * 3 * 5 * 5);
        Assert.assertEquals(Integer.valueOf(2), factors.get(2L));
        Assert.assertEquals(Integer.valueOf(1), factors.get(3L));
        Assert.assertEquals(Integer.valueOf(2), factors.get(5L));
        Assert.assertFalse(factors.containsKey(7L));

        factors = Primes.factorize(13);
        Assert.assertEquals(Integer.valueOf(1), factors.get(13L));

        factors = Primes.factorize(1);
        Assert.assertTrue(factors.isEmpty());
    }

    @Test
    public void testGetPrimeFactors() {
        List<Long> factors = Primes.getPrimeFactors(60); // 2^2 * 3 * 5
        Assert.assertEquals(3, factors.size());
        Assert.assertTrue(factors.contains(2L));
        Assert.assertTrue(factors.contains(3L));
        Assert.assertTrue(factors.contains(5L));

        factors = Primes.getPrimeFactors(100); // 2^2 * 5^2
        Assert.assertEquals(2, factors.size());
        Assert.assertTrue(factors.contains(2L));
        Assert.assertTrue(factors.contains(5L));
    }
}
