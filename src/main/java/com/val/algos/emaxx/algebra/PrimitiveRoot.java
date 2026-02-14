package com.val.algos.emaxx.algebra;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds a primitive root modulo n.
 * A primitive root is a generator of the multiplicative group of integers modulo n.
 * <p>
 * <a href="http://e-maxx.ru/algo/primitive_root">e-maxx.ru/algo/primitive_root</a>
 *
 * @author valerjanka
 */
public class PrimitiveRoot {

    /**
     * Finds the smallest primitive root modulo p.
     * Assumes p is a prime number.
     * <p>
     * Time Complexity: O(log^c(p)) where c is constant.
     * Space Complexity: O(log(p)) to store factors of p-1.
     *
     * @param p a prime number
     * @return the smallest primitive root modulo p
     */
    public static int generator(int p) {
        if (p == 2) {
            return 1;
        }

        List<Integer> factors = new ArrayList<>();
        int phi = p - 1;
        int n = phi;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            factors.add(n);
        }

        for (int res = 2; res <= p; ++res) {
            boolean ok = true;
            for (int factor : factors) {
                if (BinaryUtils.pow(res, phi / factor, p) == 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return res;
            }
        }
        return -1;
    }
}
