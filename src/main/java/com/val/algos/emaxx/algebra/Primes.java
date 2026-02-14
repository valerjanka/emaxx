package com.val.algos.emaxx.algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author valerjanka
 */
public class Primes {
    /**
     * Calculates the lowest primes divisor for every number from 2..n;
     * In parallel it calculates all primes to n.
     * We do it iteratively from 2..n.
     * int[] result at the beginning contains 0-s.
     * <p>
     * In cycle,
     * 1. if result[i] == 0 ->
     * 1.1. it is prime and result[i] = i; primes.add(i);
     * 1.2 else we have already define the lowest prime.
     * 2. for all founded primes that < result[i]:
     * result[i * prime] = prime
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * <p>
     * <a href="http://e-maxx.ru/algo/prime_sieve_linear">e-maxx.ru/algo/prime_sieve_linear</a>
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
     * Check if n is prime or not.
     * Also n is not prime if it lower than 2.
     * <p>
     * Time Complexity: O(sqrt(n))
     * Space Complexity: O(1)
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

    /**
     * Sieve of Eratosthenes to find all primes up to n.
     * <p>
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     * <p>
     * <a href="http://e-maxx.ru/algo/eratosthenes_sieve">e-maxx.ru/algo/eratosthenes_sieve</a>
     *
     * @param n upper bound
     * @return list of primes up to n
     */
    public ArrayList<Integer> calculatePrimes(int n) {
        if (n < 2) {
            return new ArrayList<>();
        }
        boolean[] isPrime = new boolean[n + 1];
        java.util.Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        int approximateSize = Math.max(16, (int) (n / Math.log(n)) + 1);
        ArrayList<Integer> result = new ArrayList<>(approximateSize);
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Factorizes a number into its prime factors.
     * <p>
     * Time Complexity: O(sqrt(n))
     * Space Complexity: O(1) auxiliary space (excluding result map)
     * <p>
     * <a href="http://e-maxx.ru/algo/factorization">e-maxx.ru/algo/factorization</a>
     *
     * @param n number to factorize
     * @return map where key is prime factor and value is its exponent
     */
    public static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> factors = new HashMap<>();
        for (long i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        if (n > 1) {
            factors.put(n, factors.getOrDefault(n, 0) + 1);
        }
        return factors;
    }

    /**
     * Returns a list of distinct prime factors of n.
     * <p>
     * Time Complexity: O(sqrt(n))
     * Space Complexity: O(1) auxiliary space (excluding result list)
     *
     * @param n number to get prime factors for
     * @return list of distinct prime factors
     */
    public static List<Long> getPrimeFactors(long n) {
        return new ArrayList<>(factorize(n).keySet());
    }
}
