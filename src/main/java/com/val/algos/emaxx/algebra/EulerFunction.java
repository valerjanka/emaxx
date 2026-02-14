package com.val.algos.emaxx.algebra;

/**
 * Euler's totient function phi(n) counts the number of positive integers less than or equal to n
 * that are relatively prime to n.
 * <p>
 * <a href="http://e-maxx.ru/algo/euler_function">e-maxx.ru/algo/euler_function</a>
 *
 * @author valerjanka
 */
public class EulerFunction {

    /**
     * Calculates Euler's totient function phi(n).
     * <p>
     * Formula: phi(n) = n * (1 - 1/p1) * ... * (1 - 1/pk), where p1..pk are prime factors of n.
     * <p>
     * Time Complexity: O(sqrt(n)) because of prime factorization.
     * Space Complexity: O(1)
     *
     * @param n the number
     * @return phi(n)
     */
    public static long phi(long n) {
        long result = n;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    /**
     * Calculates Euler's totient function for all numbers from 1 to n.
     * Uses a sieve-like approach.
     * <p>
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     *
     * @param n upper bound
     * @return array where index i contains phi(i)
     */
    public static int[] phi1ToN(int n) {
        int[] phi = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            phi[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            if (phi[i] == i) { // i is prime
                for (int j = i; j <= n; j += i) {
                    phi[j] -= phi[j] / i;
                }
            }
        }
        return phi;
    }
}
