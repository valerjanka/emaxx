package com.val.emaxx.algebra;

/**
 * Created by valerii.ryzhuk on 10/27/2015.
 */
public class Primes {
    /**
     * Calculates the lowest primes divisor for every number from 2..n;
     * In parallel it calculates all primes to n.
     * We do it iteratively from 2..n.
     * int[] result at the beginning contains 0-s.
     *
     * In cycle,
     * 1. if result[i] == 0 ->
     *  1.1. it is prime and result[i] = i; primes.add(i);
     *  1.2 else we have already define the lowest prime.
     * 2. for all founded primes that < result[i]:
     *  result[i * prime] = prime
     *
     * @param n max number
     * @return mas where i element is min prime divisor for i
     */
    public int[] lowestPrimes(int n) {
        int[] result = new int[n+1];
        int[] primes = new int[n]; // should be less
        int primeSize = 0;
        for(int i = 2; i <=n; i++) {
            if(result[i] == 0) {
                result[i] = i;
                primes[primeSize++] = i;
            }
            for(int j = 0; j < primeSize && primes[j] <= result[i] && i*primes[j] <= n; j++) {
                result[i*primes[j]] = primes[j];
            }
        }
        return result;
    }
}
