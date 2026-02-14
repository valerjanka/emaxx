package com.val.algos.emaxx.algebra;

/**
 * Chinese Remainder Theorem implementation using Garner's Algorithm.
 * Solves the system of congruences:
 * x = a1 (mod m1)
 * x = a2 (mod m2)
 * ...
 * x = ak (mod mk)
 * where m1, m2, ..., mk are pairwise coprime.
 * <p>
 * <a href="http://e-maxx.ru/algo/chinese_theorem">e-maxx.ru/algo/chinese_theorem</a>
 *
 * @author valerjanka
 */
public class ChineseRemainderTheorem {

    /**
     * Solves the system of congruences using Garner's algorithm.
     * This avoids overflow for large moduli compared to the standard construction.
     * Assumes all moduli are pairwise coprime.
     * <p>
     * Time Complexity: O(k^2) where k is the number of equations.
     * Space Complexity: O(k)
     *
     * @param a remainders
     * @param m moduli (must be pairwise coprime)
     * @return x such that x = a[i] (mod m[i]) for all i. result is modulo Product(m[i])
     */
    public static long solve(long[] a, long[] m) {
        int k = m.length;
        long[] x = new long[k];

        for (int i = 0; i < k; ++i) {
            x[i] = a[i];
            for (int j = 0; j < i; ++j) {
                long cur = (x[i] - x[j]);
                cur = (cur % m[i] + m[i]) % m[i]; // make positive modulo

                // Calculate inverse of m[j] modulo m[i]
                // Euclid.inverse throws exception if not coprime, which validates our assumption
                long inv = Euclid.inverse(m[j], m[i]);

                x[i] = (cur * inv) % m[i];
            }
        }

        long result = 0;
        long mult = 1;
        for (int i = 0; i < k; ++i) {
            result += x[i] * mult;
            // No modulo here as we want the full number, but be careful of overflow if product exceeds Long.MAX_VALUE
            // In typical competitive programming, we might want result % product(all m), but Garner allows keeping as is
            // or taking modulo if needed. Here we reconstruct the full number.
            if (i < k - 1) {
                mult *= m[i];
            }
        }
        return result;
    }
}
