package com.company.algebra;

/**
 * Created by vryzhuk on 3/8/2015.
 */
public class BinaryUtils {

    /**
     * Returns number ^ degree % mod
     * All numbers < MAX_INT
     *
     * @param number
     * @param degree
     * @param mod
     * @return
     */
    public static int pow(int number, int degree, int mod) {
        long result = 1;
        while (degree > 0) {
            if ((degree & 1) == 1) {
                result = (result * number) % mod;
            }
            number = (number * number) % mod;
            degree >>= 1;
        }
        return (int) result;
    }

    /**
     * Returns a*b / mod
     * 0 < a,b < MAX_LONG/2
     * a*b = a * (2^k1 + 2^k2 + ...)
     * @param a
     * @param b
     * @param mod
     * @return
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

    public static long binary(long number, long degree, BinaryStepAction<Long> action) {
        long result = action.zeroResult();
        while(degree > 0) {
            if((degree & 1) == 1) {
                result = action.operator(result, number);
            }
            number = action.nextNumber(number);
            degree >>= 1;
        }
        return result;
    }

    public static <T> T binary(T number, long degree, BinaryStepAction<T> action) {
        T result = action.zeroResult();
        while(degree > 0) {
            if((degree & 1) == 1) {
                result = action.operator(result, number);
            }
            number = action.nextNumber(number);
            degree >>= 1;
        }
        return result;
    }

    public interface BinaryStepAction<T> {
        T nextNumber(T number);
        T operator(T result, T number);
        T zeroResult();
    }
}
