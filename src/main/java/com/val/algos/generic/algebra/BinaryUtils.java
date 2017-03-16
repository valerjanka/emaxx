package com.val.algos.generic.algebra;

/**
 * @author valerjanka
 */
public class BinaryUtils {

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

    /**
     * Creates Long pow action for binary calculations
     * @return long pow action
     */
    public static BinaryStepAction<Long> createLongPowAction() {
        return new BinaryStepAction<Long>() {
            @Override
            public Long nextNumber(Long number) {
                return number * number;
            }

            @Override
            public Long operator(Long result, Long number) {
                return result * number;
            }

            @Override
            public Long zeroResult() {
                return 1L;
            }
        };
    }

    /**
     * Create action for binary calculation of pow by mod
     * @param mod is mod
     * @return Long Pow Action by mod
     */
    public static BinaryStepAction<Long> createLongPowAction(final long mod) {
        return new BinaryStepAction<Long>() {
            @Override
            public Long nextNumber(Long number) {
                return (number * number) % mod;
            }

            @Override
            public Long operator(Long result, Long number) {
                return (result * number) % mod;
            }

            @Override
            public Long zeroResult() {
                return 1L;
            }
        };
    }


    /**
     * Create action for binary calculation of pow by mod
     * @param mod is mod
     * @return Long Pow Action by mod
     */
    public static BinaryStepAction<Long> createLongMultiplyAction(final long mod) {
        return new BinaryStepAction<Long>() {
            @Override
            public Long nextNumber(Long number) {
                return (number + number) % mod;
            }

            @Override
            public Long operator(Long result, Long number) {
                return (result + number) % mod;
            }

            @Override
            public Long zeroResult() {
                return 0L;
            }
        };
    }

}
