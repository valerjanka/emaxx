package com.val.algos.emaxx.algebra;

import java.util.ArrayList;

/**
 * @author valerjanka
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


    /**
     * Check is n is prime or not. Complexity = sqrt(n)
     * Also n is not prime if it lower than 2
     *
     * @param n number to check primality
     * @return is prime
     */
    public boolean isPrime(int n) {
        if(n < 2) {
            return false;
        }
        int sqrt = (int)(Math.sqrt(1.0 * n) + 0.00000000001);
        for(int i = 2; i <= sqrt; i++) { // check to sqrt(n) divisors
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> calculatePrimes(int n) {
        if(n < 2) {
            return new ArrayList<>();
        }
        int approximateSize = Math.max(16, (int) (n / Math.log(n)) + 1);
        int[] primes = new int[2 * approximateSize];
        int size = 0;
        primes[size++] = 2;
        for(int p = 3; p <= n; p+=2) {
            if(isPrime(p, primes)) {
                primes[size++] = p;
            }
        }
        ArrayList<Integer> result = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            result.add(primes[i]);
        }
        return result;
    }

    private boolean isPrime(int p, int[] primes)
    {
        int sqrt = (int)(Math.sqrt(p) + 0.000000000001);
        for(int i : primes) {
            if(i > sqrt) {
                return true;
            }
            if(p % i == 0) {
                return false;
            }
        }
        return true;
    }
}
