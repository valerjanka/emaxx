package com.val.algos.emaxx.algebra;

import java.util.HashMap;
import java.util.Map;

/**
 * Solves the Discrete Logarithm Problem: a^x = b (mod m).
 * <p>
 * <a href="http://e-maxx.ru/algo/discrete_log">e-maxx.ru/algo/discrete_log</a>
 *
 * @author valerjanka
 */
public class DiscreteLogarithm {

    /**
     * Solves a^x = b (mod m) using Baby-step Giant-step algorithm.
     * Assumes a and m are coprime (gcd(a, m) = 1).
     * If they are not coprime, this implementation may not find a solution even if one exists
     * (requires extended algorithm).
     * <p>
     * Time Complexity: O(sqrt(m))
     * Space Complexity: O(sqrt(m))
     *
     * @param a base
     * @param b target
     * @param m modulus
     * @return x such that a^x = b (mod m), or -1 if no solution found
     */
    public static int solve(int a, int b, int m) {
        a %= m;
        b %= m;
        int n = (int) Math.sqrt(m) + 1;

        Map<Long, Integer> vals = new HashMap<>();
        long current = 1;
        for (int p = 1; p <= n; ++p) {
            current = (current * a) % m;
            vals.put(current * b % m, p);
        }

        long an = current; // a^n
        current = 1;
        for (int q = 1; q <= n; ++q) {
            current = (current * an) % m; // a^(n*q)
            if (vals.containsKey(current)) {
                int p = vals.get(current);
                return n * q - p;
            }
        }
        return -1;
    }
}
