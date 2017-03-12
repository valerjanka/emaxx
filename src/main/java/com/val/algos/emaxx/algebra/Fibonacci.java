package com.val.algos.emaxx.algebra;

/**
 * @author valerjanka
 */
public class Fibonacci {
    /**
     * F0 = 0; F1 = 1; F2 = 1; F3 = 2
     * Fn = Fn-1 + Fn-2
     * F2n = Fn*(Fn+1 + Fn-1)
     * <p/>
     * Calculate Fn via fast binary calculation
     * Implements next logic: (F0 F1) * (0 1, 1 1)^n = (Fn-1 Fn)
     *
     * @param n the number of Fibonacci number
     * @return nth value of Fibonacci number using fast binary exponentiation
     */
    public static long binary(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        long[][] matrix = {{0, 1}, {1, 1}};
        long[][] result = {{1, 0}, {0, 1}};
        n -= 2;
        while (n > 0) {
            if ((n & 1) == 1) {
                multiply(result, matrix);
            }
            multiply(matrix, matrix);
            n >>= 1;
        }
        return result[0][1] + result[1][1];
    }

    private static void multiply(long[][] result, long[][] matrix) {
        long a00 = result[0][0] * matrix[0][0] + result[0][1] * matrix[1][0];
        long a01 = result[0][0] * matrix[0][1] + result[0][1] * matrix[1][1];
        long a10 = result[1][0] * matrix[0][0] + result[1][1] * matrix[1][0];
        result[1][1] = result[1][0] * matrix[0][1] + result[1][1] * matrix[1][1];
        result[0][0] = a00;
        result[0][1] = a01;
        result[1][0] = a10;
    }
}
