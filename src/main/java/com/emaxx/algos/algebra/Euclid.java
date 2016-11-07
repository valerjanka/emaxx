package com.emaxx.algos.algebra;

/**
 * User: vryzhuk
 *
 * Resource: http://e-maxx.ru/algo/extended_euclid_algorithm
 * http://e-maxx.ru/algo/euclid_algorithm
 *
 * Date: 5/2/15
 * Time: 2:19 PM
 */
public class Euclid {

    /**
     * Calculates greatest common divisor recursively.
     *
     * @param a first number
     * @param b second number
     * @return GCD(a,b)
     */
    public static long gcd_recursive(long a, long b) {
        if(b == 0) {
            return a;
        } else {
            return gcd_recursive(b, a % b);
        }
    }

    /**
     * Calculate greater common divisor iteratively
     * @param a first number
     * @param b second number
     * @return GCD(a,b)
     */
    public static long gcd_iterative(long a, long b) {
        long x;
        while(b > 0) {
            x = a % b;
            a = b;
            b = x;
        }
        return a;
    }

    /**
     * Calculates least common multiplier by using gcd_iterative:
     * lcm = a / gcd * b;
     *
     * GCD can not be 0
     *
     * @param a first number
     * @param b second number
     * @return LCM(a,b)
     */
    public static long lcm(long a, long b) {
        return a / gcd_iterative(a, b) * b;
    }

    /**
     * ax+by=gcd(a,b)
     *
     * Recalculate in each recursion iteration algorithm:
     * x = y1 - b/a * x1;
     * y = x1;
     *
     * @param a first number
     * @param b second number
     * @return x,y and GCD(a,b): ax+by=gcd
     */
    public static EuclidResult gcd(long a, long b) {
        EuclidResult result;
        if(a==0) {
            result = new EuclidResult(b, 0, 1);
        } else {
            result = gcd(b%a, a);
            result.recalculate(a, b);
        }
        return result;
    }

    /**
     * Calculates coefficients ax+by=c
     * 1. c must be divisible by GCD(a,b) - else no integers x and y
     * 2. if (x0,y0) - result, then all solutions will be:
     *  x = x0 + k*b,
     *  y = y0 - k*a
     * 3. Algorithm:
     *      - if c % gcd(a,b) = 0 and c != gcd:  solve: ax+by=gcd and result will be: x0=x0*c/gcd, y0=y0*c/gcd
     *      - find x0, y0 via Euclid algorithm
     * @param a first number
     * @param b second number
     * @param c result of equation
     * @return x, y, gcd(a,b): ax+by = c
     */
    public static EuclidResult solveDiophantineEquation(long a, long b, long c) {
        long gcd = Euclid.gcd_iterative(a, b);
        if(c % gcd != 0) {
            throw new IllegalArgumentException("No integer solutions: c=" + c + " is not multiple of gcd=" + gcd);
        } else {
            EuclidResult result = Euclid.gcd(a, b);
            if(c != gcd) {
                result.x = result.x * c / gcd;
                result.y = result.y * c / gcd;
            }
            return result;
        }
    }

    /**
     * Calculates inverse= a^-1 mod m =>  a * inverse = 1 mod m.
     *
     * GCD of a and m must be 1. Otherwise - IllegalArgumentException.
     * @param a number for which we calculate inverse element
     * @param m modulo
     * @return inverse
     */
    public static long inverse(long a, long m) {
        EuclidResult result = gcd(a, m);
        if(result.gcd > 1) {
            throw new IllegalArgumentException("No inverse element for " + a + ", " + m);
        } else {
            return (result.x%m + m)%m;
        }
    }


    /**
     * Calculates inverse elements for all from 1 to m-1.
     *  Formula: result[i] = (m - (m/i) * result[m%i] % m) % m;
     * @param m prime number
     * @return array where i-th element = inverse to i element
     */
    public static int[] inverse(int m) {
        int[] result = new int[m];
        result[1] = 1;
        for(int i = 2; i < m; i++) {
            result[i] = (m - (m/i) * result[m%i] % m) % m;
        }
        return result;
    }

    static class EuclidResult {
        long gcd = 1;
        long x,y;
        private long x1,y1;

        EuclidResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }

        public void recalculate(long a, long b) {
            x1 = x;
            y1 = y;
            x = y1 - (b /a) * x1;
            y = x1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EuclidResult that = (EuclidResult) o;

            if (gcd != that.gcd) return false;
            if (x != that.x) return false;
            if (y != that.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (gcd ^ (gcd >>> 32));
            result = 31 * result + (int) (x ^ (x >>> 32));
            result = 31 * result + (int) (y ^ (y >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "EuclidResult{" +
                    "gcd=" + gcd +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
