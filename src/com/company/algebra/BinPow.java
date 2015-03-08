package com.company.algebra;

/**
 * Created by Luba on 3/8/2015.
 */
public class BinPow {
    public static int binpow(int number, int degree, int mod) {
        long result = 1;
        while (degree > 0) {
            if ((degree & 1) == 1) {
                result = (result * number) % mod;
            }
            number = (number * number) % mod;
            degree >>= 2;
        }
        return (int) result;
    }
}
