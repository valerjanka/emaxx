package com.val.algos.emaxx.algebra;

/**
 * @author valerjanka
 */
public class BinaryUtils {

    /**
     * Returns number ^ degree % mod
     * All numbers < MAX_INT
     *
     * @param number to be powered
     * @param degree to which power the number
     * @param mod by which return the result
     * @return number in the degree by modulo
     */
    public static int pow(int number, int degree, int mod) {
        long result = 1;
        while (degree > 0) {
            if ((degree & 1) == 1) {
                result = (result * number) % mod;
            }
            number = (int)(((long) number * number) % mod);
            degree >>= 1;
        }
        return (int) result;
    }

    /**
     * Returns a*b % mod
     * 0 < a,b < MAX_LONG/2
     * a*b = a * (2^k1 + 2^k2 + ...)
     * @param a first factor
     * @param b second factor
     * @param mod modulo
     * @return a product of multiplication by module
     */
    public static long multiply(long a, long b, long mod) {
        long result = 0;
        while(b > 0) {
            if((b & 1) == 1) {
                result = (result + a) % mod;
            }
            a = (a + a) % mod;
            b >>= 1;
        }
        return result;
    }

}
